package br.com.zupacademy.polyana.mercadolivre.repository;

import br.com.zupacademy.polyana.mercadolivre.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
