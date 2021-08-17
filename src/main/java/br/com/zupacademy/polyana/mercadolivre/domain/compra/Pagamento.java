package br.com.zupacademy.polyana.mercadolivre.domain.compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusPagamento status;
    @NotBlank
    private String idGatewayPagamento;
    @NotNull
    private LocalDateTime instante;
    @ManyToOne @NotNull @Valid
    private Compra compra;

    @Deprecated
    public Pagamento() {

    }

    public Pagamento(@NotNull StatusPagamento status,
                     @NotBlank String idGatewayPagamento, @NotNull @Valid Compra compra) {
        this.status = status;
        this.idGatewayPagamento = idGatewayPagamento;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidoComSucesso() {
        return this.status.equals(StatusPagamento.sucesso);
    }

    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", status=" + status
                + ", idGatewayPagamento=" + idGatewayPagamento + ", instante="
                + instante + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return idGatewayPagamento.equals(pagamento.idGatewayPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGatewayPagamento);
    }
}