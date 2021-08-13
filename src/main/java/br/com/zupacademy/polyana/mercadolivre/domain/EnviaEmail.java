package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;

@Component
public interface EnviaEmail {

    void enviar(String corpo, String assunto, String emailCliente, String emailVendedor);
}
