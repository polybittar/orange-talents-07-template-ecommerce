package br.com.zupacademy.polyana.mercadolivre.controller.produtocontroller;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Categoria;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.CategoriaRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public void cadastrar(@Valid @RequestBody CategoriaRequest categoriaRequest) {

        Categoria categoria = categoriaRequest.converter(entityManager);
        entityManager.persist(categoria);
    }
}
