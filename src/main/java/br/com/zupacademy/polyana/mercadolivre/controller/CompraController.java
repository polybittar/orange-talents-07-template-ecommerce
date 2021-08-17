package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.email.Email;
import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.compradto.CompraRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;
    private Email email;


    public CompraController(Email email) {
        this.email = email;
    }

    @PostMapping
    @Transactional
    public String salvar(@RequestBody CompraRequest compraRequest,
                         @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uri) throws BindException {
        Compra compra = compraRequest.converter(usuarioLogado, manager);
        manager.persist(compra);
        email.novaCompra(compra);
        return compra.urlRedirecionamento(uri);
    }
 }
