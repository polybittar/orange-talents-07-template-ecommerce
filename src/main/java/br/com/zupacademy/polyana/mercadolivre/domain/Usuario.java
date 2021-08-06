package br.com.zupacademy.polyana.mercadolivre.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }
}
