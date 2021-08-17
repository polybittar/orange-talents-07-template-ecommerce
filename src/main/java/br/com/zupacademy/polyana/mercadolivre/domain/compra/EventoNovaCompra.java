package br.com.zupacademy.polyana.mercadolivre.domain.compra;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.EventoCompraSucesso;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoNovaCompra {

    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public EventoNovaCompra(Set<EventoCompraSucesso> eventosCompraSucesso) {
        this.eventosCompraSucesso = eventosCompraSucesso;
    }

    public void processar(Compra compra) {
        if(compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }else{

        }
    }
}