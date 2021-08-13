package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EmailFake implements EnviaEmail{


    @Override
    public void enviar(String corpo, @Valid @NotNull Pergunta pergunta, String emailCliente, String emailVendedor) {
        System.out.println(corpo);
        System.out.println(pergunta.getTitulo());
        System.out.println(emailCliente);
        System.out.println(emailVendedor);
    }
}
