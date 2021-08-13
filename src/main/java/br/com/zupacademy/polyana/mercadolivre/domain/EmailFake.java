package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;

@Component
public class EmailFake implements EnviaEmail{


    @Override
    public void enviar(String corpo, String assunto, String emailCliente, String emailVendedor) {
        System.out.println(corpo);
        System.out.println(assunto);
        System.out.println(emailCliente);
        System.out.println(emailVendedor);
    }
}
