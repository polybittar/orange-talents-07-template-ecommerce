package br.com.zupacademy.polyana.mercadolivre.config.security;

import java.util.Date;

import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	
	@Value("${forum.jwt.expiration}")													//application.properties
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();  						// carrega o usuario logado
		Date hoje = new Date(); 														// data de geração do token
		Date dataExpiracao = new Date(hoje.getTime()+Long.parseLong(expiration));		// soma a data com o tempo de expiracao
		return Jwts.builder()															// gera o token
				.setIssuer("API do Mercado Livre")										// quem gera o token
				.setSubject(logado.getId().toString())									// usuario do token
				.setIssuedAt(hoje)														// atribui data de geração do token
				.setExpiration(dataExpiracao)											// tempo de expiração do token
				.signWith(SignatureAlgorithm.HS256, secret)								// criptografa token
				.compact();																// compacta e transforma em uma string
	}

	public boolean isTokenValido(String token) {
		try {
			
		Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
