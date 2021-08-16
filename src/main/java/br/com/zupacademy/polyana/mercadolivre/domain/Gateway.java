package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {
    PAGSEGURO {                     //fazendo a lógica do retorno da url fora do controller
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro;       //dominio + id da compra + redirect + gateway com id
        }
    },
    PAYPAL {
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);      //método abstrato implementado acima
}
