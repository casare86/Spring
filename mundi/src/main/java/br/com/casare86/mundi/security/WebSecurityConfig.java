package br.com.casare86.mundi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.httpBasic(); //just a alert/prompt-like window
//			.formLogin(form -> 
//				form.loginPage("/login")
//				.permitAll()
//			);
			
			//.logout(logout -> logout.logoutUrl("/logout"));
			//NOT A BEST PRACTICE - logout by default must be a POST method for best security. 
			//IF CSRF is disabled then you can use Get/Post as you wish for logout
			//.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")));
		
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		
		//no treatment to password insecure for production environment
		UserDetails userDefault = User.withDefaultPasswordEncoder()
			     .username("tester")
			     .password("123")
			     .roles("USER")
			     .build();
		
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserDetails user = User.withUsername("tester")
//				.password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG") //password
//				.roles("USER")
//				.build();
//		
		
		return new InMemoryUserDetailsManager(userDefault);
	}
}
