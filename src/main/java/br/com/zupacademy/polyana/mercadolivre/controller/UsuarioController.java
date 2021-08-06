package br.com.zupacademy.polyana.mercadolivre.controller;

import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import br.com.zupacademy.polyana.mercadolivre.dto.UsuarioRequest;
import br.com.zupacademy.polyana.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.save(usuarioRequest.converter());

        return ResponseEntity.ok().build();
    }
}

