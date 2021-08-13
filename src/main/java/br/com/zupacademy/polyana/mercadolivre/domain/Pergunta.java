package br.com.zupacademy.polyana.mercadolivre.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta implements Comparable<Pergunta>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @ManyToOne @NotNull @Valid
    private Usuario usuario;
    @ManyToOne @NotNull @Valid
    private Produto produto;
    private LocalDateTime data;

    public Pergunta(String titulo, Usuario usuario, Produto produto, LocalDateTime data) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.data = data;
    }

    @Deprecated
    public Pergunta() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public int compareTo(Pergunta o) {              //ver se duas perguntas são iguais
        return this.titulo.compareTo(o.titulo);
    }
}
