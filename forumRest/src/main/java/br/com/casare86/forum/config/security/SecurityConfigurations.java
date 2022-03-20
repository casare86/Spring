package br.com.casare86.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.casare86.forum.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired //precisa ser injetada a dependencia para ser utilizada na AutenticacaoTokenFilter
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService)
		.passwordEncoder(new BCryptPasswordEncoder()); //não transita a senha desprotegida e sim seu hash
	}
	
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/**").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers("/h2-console/**").permitAll() //permissões para acessar os endpoints 
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //verificar status da aplicação - configurar para DEVs apenas (permissão de administração do sistema de preferencia
		.anyRequest().authenticated() //todos os usuários e acessos devem estar autenticados
		.and().csrf().disable() //Cross Site Request Force - evita ataques hackers, por ser stateless não precisa ficar habilitado
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //evita criação SESSION para cada request (evita sobrecarga no servidor)
		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); //adiciona nosso filtro antes de seguir com o padrão do spring
		
		// .formLogin() //gera uma página com form para autorização de usuários
		
		http.headers().frameOptions().disable();//necessário para evitar problemas com o carregamento do H2(BD)
	}
	
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		
	}
	
}
