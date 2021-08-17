package br.com.zupacademy.polyana.mercadolivre.controller.produtocontroller;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Pergunta;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.email.Email;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.PerguntaRequest;
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
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;
    private Email email;

    public PerguntaController(Email email) {
        this.email = email;
    }

    @PostMapping("/api/v1/produtos/{id}/perguntas")
    @Transactional
    public void cadastrar(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest perguntaRequest, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = manager.find(Produto.class, id);
        if(!produto.pertenceAoUsuario(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Pergunta pergunta = perguntaRequest.converter(usuarioLogado,produto);
        manager.persist(pergunta);
        email.pergunta(pergunta);
    }
}