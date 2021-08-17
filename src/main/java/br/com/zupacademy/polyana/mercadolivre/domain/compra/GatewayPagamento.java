package br.com.zupacademy.polyana.mercadolivre.domain.compra;

public interface GatewayPagamento {
    Pagamento converter(Compra compra);
}
