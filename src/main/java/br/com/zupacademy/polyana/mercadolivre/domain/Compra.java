package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private CompraStatus status;

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @Positive
    @NotNull
    private int quantidade;

    public Compra(Gateway gateway, Produto produto, Usuario usuarioLogado, int quantidade) {
        this.gateway = gateway;
        this.produto = produto;
        this.usuario = usuarioLogado;
        this.quantidade = quantidade;
        this.status = CompraStatus.INICIADA;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public CompraStatus getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }
}
