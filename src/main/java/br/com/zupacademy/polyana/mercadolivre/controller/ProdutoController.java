package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.CaracteristicasDiferentesValidator;
import br.com.zupacademy.polyana.mercadolivre.domain.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.ProdutoRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @InitBinder(value = "ProdutoRequest")
    public  void init(WebDataBinder binder){
        binder.addValidators(new CaracteristicasDiferentesValidator());
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,@AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = produtoRequest.converter(manager, usuarioLogado);
        manager.persist(produto);
    }
}

