package br.com.zupacademy.polyana.mercadolivre.controller.produtocontroller;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Opiniao;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.OpiniaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/api/v1/produtos/{id}/opinioes")
    @Transactional
    public void cadastrar(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest, @AuthenticationPrincipal Usuario usuarioLogado) {  //referencia ao usuario ja autenticado
        Usuario usuario = usuarioLogado;
        Produto produtoProcura = manager.find(Produto.class, id);
        if(!produtoProcura.pertenceAoUsuario(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Opiniao opiniao = opiniaoRequest.converter(usuario,produtoProcura);
        manager.persist(opiniao);
    }
}