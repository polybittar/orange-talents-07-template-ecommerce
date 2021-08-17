package br.com.zupacademy.polyana.mercadolivre.dto.compradto;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.GatewayPagamento;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.Pagamento;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.StatusPagseguro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroRequest implements GatewayPagamento {

    @NotBlank
    private String idPagamento;
    @NotNull
    private StatusPagseguro status;

    public PagseguroRequest(@NotBlank String idPagamento,
                                   StatusPagseguro status) {
        super();
        this.idPagamento = idPagamento;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagseguroRequest [idPagamento=" + idPagamento
                + ", status=" + status + "]";
    }

    public Pagamento converter(Compra compra) {
        return new Pagamento(status.normaliza(), idPagamento, compra);
    }
}
