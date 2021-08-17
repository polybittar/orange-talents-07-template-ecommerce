package br.com.zupacademy.polyana.mercadolivre.controller.pagamentocontroller;

import br.com.zupacademy.polyana.mercadolivre.dto.compradto.NotaFiscalRequest;
import br.com.zupacademy.polyana.mercadolivre.dto.compradto.RankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping("/nota-fiscal")
    public void criaNota(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest) throws InterruptedException {
        System.out.println("criando nota: " + notaFiscalRequest);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingRequest rankingRequest) throws InterruptedException {
        System.out.println("criando ranking: " + rankingRequest);
        Thread.sleep(150);
    }

}