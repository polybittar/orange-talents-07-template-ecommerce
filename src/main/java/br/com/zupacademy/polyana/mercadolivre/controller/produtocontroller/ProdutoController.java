package br.com.zupacademy.polyana.mercadolivre.controller.produtocontroller;

import br.com.zupacademy.polyana.mercadolivre.config.validation.validator.CaracteristicasDiferentesValidator;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.DetalhesResponse;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.ProdutoRequest;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesResponse> detalhar(@PathVariable("id")Long id){
        Produto produto = manager.find(Produto.class,id);
        return ResponseEntity.ok().body(new DetalhesResponse(produto));
    }
}