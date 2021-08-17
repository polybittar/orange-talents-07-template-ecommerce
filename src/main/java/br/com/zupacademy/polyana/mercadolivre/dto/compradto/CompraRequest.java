package br.com.zupacademy.polyana.mercadolivre.dto.compradto;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.Gateway;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import org.springframework.validation.BindException;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @Positive
    private Long idProduto;

    @NotNull
    @Positive
    private int quantidade;

    public CompraRequest(Gateway gateway, Long idProduto, int quantidade) {
        this.gateway = gateway;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }


    public Compra converter(Usuario usuarioLogado, EntityManager manager) throws BindException {
        Produto produto = manager.find(Produto.class, idProduto);
        boolean abateu = produto.diminuiEstoque(quantidade);
        if(abateu) {
            //manager.merge(produto);                     (não precisa, o hibernate faz o update)
            Compra novaCompra = new Compra(gateway, produto, usuarioLogado, quantidade);
            return novaCompra;
        }
        BindException bind = new BindException(produto, "CompraRequest");           //BindException gera um json da exception
        bind.reject(null, "Estoque está indisponível");
        throw bind;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
