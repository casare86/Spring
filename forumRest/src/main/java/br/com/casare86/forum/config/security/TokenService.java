package br.com.casare86.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.casare86.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	
	//@Value segue a notação de variáveis da JSP e pode vir com valor default caso não encontre a chave ${key:valor}
	@Value("${forum.jwt.expiration:180000}") //prazo de validade da API em milisegundos
	private String expiration;
	
	@Value("${forum.jwt.secret:teste123}") //secret é a chave da sua API - usar algoritimos para gerar strings grandes (256)
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API do forum da Alura - Curso Rest") //Emissor da API
				.setSubject(logado.getId().toString()) 	//nome do usuário que requisitou
				.setIssuedAt(hoje) 						//data emissão
				.setExpiration(dataExpiracao)			//data expiração - prazo de validade
				.signWith(SignatureAlgorithm.HS256, secret) //hash de login para usuario
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			
			System.out.println("Retornei true");
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		//parser do JWTS para recuperar o ID do usuário, que foi gerado junto com o token no momento da criação
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
