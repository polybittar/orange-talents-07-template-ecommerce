package br.com.zupacademy.polyana.mercadolivre.dto;


import br.com.zupacademy.polyana.mercadolivre.domain.CaracteristicaProduto;
import br.com.zupacademy.polyana.mercadolivre.domain.Produto;
import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto converter(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}