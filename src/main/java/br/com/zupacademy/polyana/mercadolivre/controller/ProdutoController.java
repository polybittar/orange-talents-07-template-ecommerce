package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.CaracteristicasDiferentesValidator;
import br.com.zupacademy.polyana.mercadolivre.domain.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.UploadFake;
import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.ImagemRequest;
import br.com.zupacademy.polyana.mercadolivre.dto.ProdutoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    private UploadFake uploadFake;

    public ProdutoController(UploadFake uploadFake) {
        this.uploadFake = uploadFake;
    }

    @InitBinder(value = "ProdutoRequest")           //não usar outras reqquest para fazer a validação
    public  void init(WebDataBinder binder){
        binder.addValidators(new CaracteristicasDiferentesValidator());
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,@AuthenticationPrincipal Usuario usuarioLogado) {  //referencia ao usuario ja autenticado
        Produto produto = produtoRequest.converter(manager, usuarioLogado);
        manager.persist(produto);
    }


    @PostMapping("/{id}/imagens")
    @Transactional
    public void adicionaImagens(@PathVariable("id") Long id, @Valid ImagemRequest imagemRequest,
                                @AuthenticationPrincipal Usuario usuarioLogado){
        Set<String> links = uploadFake.enviar(imagemRequest.getImagens());  //enviar imagens para o local onde eles vao ficar
                                                                            // pegar os links de todas as imagens
        Produto produto = manager.find(Produto.class, id);
        if(!produto.pertenceAoUsuario(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.associaImagens(links);                                      //associar esses links com o produto
        manager.merge(produto);                                             // atualizar a nova versao do produto
    }

    @GetMapping("produto/{id}")
    public void detalhaProdutos(@PathVariable("id")Long id){
        Produto produto = (Produto) manager.createQuery("select u from Produto u where u.id = :id").setParameter("id", id).getSingleResult();
        manager.persist(produto);
    }
}