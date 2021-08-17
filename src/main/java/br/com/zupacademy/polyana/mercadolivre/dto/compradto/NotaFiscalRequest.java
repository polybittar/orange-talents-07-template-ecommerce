package br.com.zupacademy.polyana.mercadolivre.dto.compradto;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idUsuario;

    public NotaFiscalRequest(Long idCompra, Long idUsuario) {
        super();
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }


    @Override
    public String toString() {
        return "NotaFiscalRequest [idCompra=" + idCompra + ", idUsuario="
                + idUsuario + "]";
    }


    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idUsuario;
    }

}