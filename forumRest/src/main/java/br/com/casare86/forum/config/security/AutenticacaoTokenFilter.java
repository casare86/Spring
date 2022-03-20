package br.com.casare86.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.casare86.forum.modelo.Usuario;
import br.com.casare86.forum.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {
	//não pode ser injetada aqui, portanto a dependencia deve ser injetada no builder e passada ao ser criada
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		System.out.println(token);
		
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarCliente(token);
		}
		//ao finalizar o processo usar o doFilter para dar sequencia
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		//precisa verificar se o usuário foi encontrado e transformado em objeto corretamente.
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		//UsernamePassordAuthenticationToken pode utilizar o usuário e seus perfis sem precisar de senha, pois já está autenticado (pelo token)
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(StringUtils.isNullOrEmpty(token) || !token.startsWith("Bearer ")) 
			return null;
		
		//retorna apenas o hash do token
		return token.substring(7, token.length());
	}
}