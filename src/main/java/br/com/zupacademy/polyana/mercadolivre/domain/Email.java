package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Email {

    private EnviaEmail enviaEmail;

    public Email(EnviaEmail enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    public void pergunta(@Valid @NotNull Pergunta pergunta) {
        enviaEmail.enviar("<html></html>","Você recebeu uma nova pergunta: ",pergunta.getUsuario().getLogin(),"vendedor@email.com");
    }
}
