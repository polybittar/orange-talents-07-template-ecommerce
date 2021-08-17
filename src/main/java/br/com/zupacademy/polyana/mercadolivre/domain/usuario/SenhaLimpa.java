package br.com.zupacademy.polyana.mercadolivre.domain.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;


public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {

        Assert.hasText(senha, "Insira uma senha");
        Assert.isTrue(senha.length() >= 6, "Insira uma senha de no mínimo 6 caracteres");

        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
