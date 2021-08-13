package br.com.zupacademy.polyana.mercadolivre.dto;

import br.com.zupacademy.polyana.mercadolivre.domain.Opiniao;
import br.com.zupacademy.polyana.mercadolivre.domain.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;


import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull @Max(5) @Min(1)
    private  Integer nota;
    @NotBlank @Size(max = 20)
    private  String titulo;
    @NotBlank @Size(max = 1000)
    private String descricao;

    @Deprecated
    public OpiniaoRequest() {
    }

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao converter(Usuario usuario, Produto produto) {
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

}