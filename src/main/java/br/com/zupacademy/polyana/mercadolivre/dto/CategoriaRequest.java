package br.com.zupacademy.polyana.mercadolivre.dto;

import br.com.zupacademy.polyana.mercadolivre.domain.Categoria;
import br.com.zupacademy.polyana.mercadolivre.domain.SenhaLimpa;
import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import br.com.zupacademy.polyana.mercadolivre.validation.validator.ExistsId;
import br.com.zupacademy.polyana.mercadolivre.validation.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;
    @ExistsId(fieldName = "categoriaMaeId",domainClass = Categoria.class)
    private Long categoriaMaeId;


    public CategoriaRequest(String nome,Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria converter(EntityManager entityManager) {
        Categoria categoria = new Categoria(nome);

        if(categoriaMaeId != null){
            Categoria categoriaMae = entityManager.find(Categoria.class, categoriaMaeId);
            Assert.state(categoriaMae != null, "A categoria mãe é nula "+categoriaMaeId);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
