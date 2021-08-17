package br.com.zupacademy.polyana.mercadolivre.dto.compradto;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.GatewayPagamento;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.Pagamento;
import br.com.zupacademy.polyana.mercadolivre.domain.compra.StatusPagamento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalRequest implements GatewayPagamento {

    @Min(0) @Max(1)
    private int status;
    @NotBlank
    private String idPagamento;

    public PaypalRequest(@Min(0) @Max(1) int status, @NotBlank String idPagamento) {
        super();
        this.status = status;
        this.idPagamento = idPagamento;
    }

    public Pagamento converter(Compra compra) {
        StatusPagamento statusCalculado = this.status == 0 ? StatusPagamento.erro
                : StatusPagamento.sucesso;

        return new Pagamento(statusCalculado, idPagamento, compra);
    }
}
