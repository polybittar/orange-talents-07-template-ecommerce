package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public interface EnviaEmail {

    void enviar(String assunto, @Valid @NotNull String corpo, String emailCliente, String emailEcommerce, String emailVendedor);
}
