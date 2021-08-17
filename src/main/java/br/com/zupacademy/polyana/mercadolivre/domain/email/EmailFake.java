package br.com.zupacademy.polyana.mercadolivre.domain.email;

import org.springframework.stereotype.Component;

@Component
public class EmailFake implements EnviaEmail{

    @Override
    public void enviar(String assunto, String corpo, String emailCliente, String emailEcommerce, String emailVendedor) {
        System.out.println(assunto);
        System.out.println(corpo);
        System.out.println(emailCliente);
        System.out.println(emailEcommerce);
        System.out.println(emailVendedor);
    }
}
