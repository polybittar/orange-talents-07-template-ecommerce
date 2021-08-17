package br.com.zupacademy.polyana.mercadolivre.dto.securitydto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticacaoRequest {


    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
