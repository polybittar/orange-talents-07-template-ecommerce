package br.com.zupacademy.polyana.mercadolivre.controller.pagamentocontroller;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.EventoNovaCompra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.GatewayPagamento;
import br.com.zupacademy.polyana.mercadolivre.dto.compradto.PagseguroRequest;
import br.com.zupacademy.polyana.mercadolivre.dto.compradto.PaypalRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PagamentoController {

    @PersistenceContext
    private EntityManager manager;
    private EventoNovaCompra eventoNovaCompra;

    public PagamentoController(EventoNovaCompra eventoNovaCompra) {
        this.eventoNovaCompra = eventoNovaCompra;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid PagseguroRequest pagseguroRequest) {
        return processa(idCompra, pagseguroRequest);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid PaypalRequest paypalRequest) {
        return processa(idCompra, paypalRequest);
    }

    private String processa(Long idCompra, GatewayPagamento gatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaPagamento(gatewayPagamento);
        manager.merge(compra);
        eventoNovaCompra.processar(compra);

        return compra.toString();
    }
}
