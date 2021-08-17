package br.com.zupacademy.polyana.mercadolivre.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter { 		// classe chamada uma unica vez por requisicao

	private AutenticacaoManager autenticacaoManager;
	private TokenManager tokenManager;

	public AutenticacaoViaTokenFilter(TokenManager tokenManager, AutenticacaoManager autenticacaoManager) {
		this.tokenManager = tokenManager;
		this.autenticacaoManager = autenticacaoManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
									FilterChain filterChain)
			throws ServletException, IOException {


		String token = recuperarToken(httpServletRequest);				//pega o token do cabeçalho
		boolean valido = tokenManager.isTokenValido(token);

		if(valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);  // segue com a requisicao
	}

	private void autenticarCliente(String token) { 						// autentica a partir do token do usuario
		Long usuarioId = tokenManager.getUsuarioId(token);				// identifica do usuario
		Usuario usuario = autenticacaoManager.findById(usuarioId);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		if(token==null || token.isEmpty() || !token.startsWith("Bearer")) {		// verifica se esta correto
			return null;
		}
		return token.substring(7, token.length());
	}

}
