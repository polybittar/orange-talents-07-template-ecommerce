package br.com.zupacademy.polyana.mercadolivre.domain;

import br.com.zupacademy.polyana.mercadolivre.validation.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Email
    private String login;
    @NotBlank @Length(min=6)
    private String senha;
    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, SenhaLimpa senhaLimpa) {

        Assert.hasText(login, "Insira um email de login");

        this.login = login;
        this.senha = senhaLimpa.hash();
    }
}
