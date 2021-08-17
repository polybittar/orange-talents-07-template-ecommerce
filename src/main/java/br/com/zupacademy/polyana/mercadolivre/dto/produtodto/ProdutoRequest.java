package br.com.zupacademy.polyana.mercadolivre.dto.produtodto;

import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.ExistsId;
import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.UniqueValue;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Categoria;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.CaracteristicaProdutoRequest;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Positive
    private int quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    private LocalDateTime data = LocalDateTime.now();

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;


    @Size(min = 3)
    private List<CaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();

    @Deprecated
    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, BigDecimal valor, int quantidade, String descricao, LocalDateTime data, Long idCategoria, List<CaracteristicaProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.data = data;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);           //a lista recebe todas as caracteristicas que chegam
    }

    public Produto converter(EntityManager manager, Usuario usuario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        return new Produto(nome, quantidade, valor, descricao, categoria, data, usuario, caracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet();
        HashSet<String> resultados = new HashSet();
        for(CaracteristicaProdutoRequest caracteristica: caracteristicas){
            String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)){
                resultados.add(nome);       //adiciona somente quando não tiver uma já com o mesmo nome
            }
        }
        return resultados;
    }

    public List<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }
}