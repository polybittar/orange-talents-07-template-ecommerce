package br.com.zupacademy.polyana.mercadolivre.dto.produtodto;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.CaracteristicaProduto;

public class DetalhesCaracteristicaResponse {

    private String nome;
    private String descricao;

    public DetalhesCaracteristicaResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
