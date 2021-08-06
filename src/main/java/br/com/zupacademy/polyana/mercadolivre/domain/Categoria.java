package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        Assert.state(nome != null, "Dê um nome à categoria");
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        Assert.state(categoriaMae != null, "A categoria mãe é nula");
        this.categoriaMae = categoriaMae;
    }
}
