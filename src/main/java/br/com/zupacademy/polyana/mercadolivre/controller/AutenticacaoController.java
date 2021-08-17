package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.config.security.TokenManager;
import br.com.zupacademy.polyana.mercadolivre.dto.securitydto.AutenticacaoRequest;
import br.com.zupacademy.polyana.mercadolivre.dto.securitydto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @PostMapping
    public ResponseEntity<?> autenticacar(@RequestBody AutenticacaoRequest autenticacaoRequest) {
        try{
            //converte os dados em um UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = autenticacaoRequest.converter();
            //verifica se o usuario existe do banco e se a senha está criptografada
            Authentication autenticate = authenticationManager.authenticate(authenticationToken);

            //gera o token
            String token = tokenManager.gerarToken(autenticate);

            return ResponseEntity.ok(new TokenResponse(token, "Bearer "));
        }catch (AuthenticationException e){

            return ResponseEntity.badRequest().build();
        }
    }
}
