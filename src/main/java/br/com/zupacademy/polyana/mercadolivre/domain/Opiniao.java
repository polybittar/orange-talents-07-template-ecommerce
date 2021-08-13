package br.com.zupacademy.polyana.mercadolivre.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Max(5) @Min(1)
    private  Integer nota;
    @NotBlank @Length(max = 20)
    private  String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}