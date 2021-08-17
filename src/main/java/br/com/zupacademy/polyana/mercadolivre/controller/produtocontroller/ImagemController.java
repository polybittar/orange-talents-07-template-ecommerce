package br.com.zupacademy.polyana.mercadolivre.controller.produtocontroller;

import br.com.zupacademy.polyana.mercadolivre.domain.produto.Produto;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.UploadFake;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.produtodto.ImagemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/produtos")
public class ImagemController {

    @PersistenceContext
    private EntityManager manager;
    private UploadFake uploadFake;

    public ImagemController(UploadFake uploadFake) {
        this.uploadFake = uploadFake;
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
}