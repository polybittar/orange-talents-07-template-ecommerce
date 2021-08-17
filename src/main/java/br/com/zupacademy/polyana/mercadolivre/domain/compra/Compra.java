package br.com.zupacademy.polyana.mercadolivre.domain.compra;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull @Valid @ManyToOne
    private Produto produto;
    @NotNull @Valid @ManyToOne
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private CompraStatus status;
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    @Positive @NotNull
    private int quantidade;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Pagamento> pagamentos = new HashSet<>();

    @Deprecated
    public Compra() {

    }

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

    @Override
    public String toString() {
        return "Compra [id=" + id + ", produtoEscolhido=" + produto
                + ", quantidade=" + quantidade + ", comprador=" + usuario
        + ", gateway=" + gateway + ", pagamentos="
                + pagamentos + "]";
    }


    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaPagamento(@Valid GatewayPagamento gatewayPagamento) {
        Pagamento novoPagamento = gatewayPagamento.converter(this);

        Assert.isTrue(!this.pagamentos.contains(novoPagamento),
                "Já existe um pagamento igual a esse processada: " + novoPagamento);
        Assert.isTrue(pagamentosConcluidosComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");

        this.pagamentos.add(novoPagamento);
    }

    private Set<Pagamento> pagamentosConcluidosComSucesso() {
        Set<Pagamento> pagamentosConcluidosComSucesso = this.pagamentos.stream()
                .filter(Pagamento::concluidoComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(pagamentosConcluidosComSucesso.size() <= 1,"Mais de um pagamento concluído com sucesso: "+this.id);

        return pagamentosConcluidosComSucesso;
    }

    public boolean processadaComSucesso() {
        return !pagamentosConcluidosComSucesso().isEmpty();
    }

}
