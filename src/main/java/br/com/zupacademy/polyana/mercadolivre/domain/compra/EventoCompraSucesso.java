package br.com.zupacademy.polyana.mercadolivre.domain.compra;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}