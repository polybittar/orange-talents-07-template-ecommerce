package br.com.zupacademy.polyana.mercadolivre.dto;

import br.com.zupacademy.polyana.mercadolivre.domain.SenhaLimpa;
import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email @UniqueValue(fieldName = "login", domainClass = Usuario.class)
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;


    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        return new Usuario(login, new SenhaLimpa(senha));
    }
}
