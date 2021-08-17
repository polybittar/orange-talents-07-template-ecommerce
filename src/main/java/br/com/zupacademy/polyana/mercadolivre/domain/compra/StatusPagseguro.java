package br.com.zupacademy.polyana.mercadolivre.domain.compra;

public enum StatusPagseguro {
    SUCESSO,ERRO;

    public StatusPagamento normaliza() {
        if (this.equals(SUCESSO)){
            return StatusPagamento.sucesso;
        }
        return StatusPagamento.erro;
    }
}
