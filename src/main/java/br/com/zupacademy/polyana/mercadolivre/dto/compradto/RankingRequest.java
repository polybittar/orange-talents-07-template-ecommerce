package br.com.zupacademy.polyana.mercadolivre.dto.compradto;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }
    @Override
    public String toString() {
        return "RankingRequest [idCompra=" + idCompra + ", idVendedor="
                + idVendedor + "]";
    }
}
