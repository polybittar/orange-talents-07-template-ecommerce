package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.dto.UsuarioRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        entityManager.persist(usuarioRequest.converter());
    }
}

