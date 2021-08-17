package br.com.zupacademy.polyana.mercadolivre.dto.produtodto;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Categoria;
import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.ExistsId;
import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }
}
