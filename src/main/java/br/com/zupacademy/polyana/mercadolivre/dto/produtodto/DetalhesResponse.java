package br.com.zupacademy.polyana.mercadolivre.dto.produtodto;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Opinioes;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;

import java.math.BigDecimal;
import java.util.*;

public class DetalhesResponse {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private Set<DetalhesCaracteristicaResponse> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private int total;

    public DetalhesResponse(Produto produto) {
        Opinioes opinioes = produto.getOpinioes();
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto
                .mapeiaCaracteristicas(DetalhesCaracteristicaResponse::new);            //pega a coleção de caracteristicas que tem dentro do produto e mapear pro que quisermos. além disso não expomos a coleção.
        this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());          //pega só o link das imagens
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());         //map com duas strings, uma pro titulo e outra pra descricao
        });
        this.mediaNotas = opinioes.media();
        this.total = opinioes.total();
    }

    public int getTotal() {
        return total;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<DetalhesCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

}
