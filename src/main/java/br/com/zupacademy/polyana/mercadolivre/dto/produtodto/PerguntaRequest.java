package br.com.zupacademy.polyana.mercadolivre.dto.produtodto;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Pergunta;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    private LocalDateTime data = LocalDateTime.now();

    @Deprecated
    public PerguntaRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Pergunta converter(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto,data);
    }

}