package br.com.zupacademy.polyana.mercadolivre.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private AutenticacaoManager autenticacaoManager;
	private TokenManager tokenManager;


	public SecurityConfigurations(AutenticacaoManager autenticacaoManager, TokenManager tokenManager) {
		this.autenticacaoManager = autenticacaoManager;
		this.tokenManager = tokenManager;
	}

	@Override @Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//Autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoManager).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/usuarios").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/categorias").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/produtos").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenManager, autenticacaoManager),
						UsernamePasswordAuthenticationFilter.class);
		
	}
}