package br.com.zupacademy.polyana.mercadolivre.config.security;

import br.com.zupacademy.polyana.mercadolivre.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Component
public class AutenticacaoManager implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Query query = manager.createQuery("select u from Usuario u where u.login = :plogin");
		List<Usuario> usuario = query.setParameter("plogin", login).getResultList();
		if(usuario.isEmpty()){
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return usuario.get(0);
	}


	public Usuario findById(Long usuarioId) {
		Query query = manager.createQuery("select u from Usuario u where u.id =:pusuarioId");
		List<Usuario> usuario = query.setParameter("pusuarioId", usuarioId).getResultList();
		if(usuario.isEmpty()){
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return usuario.get(0);
	}
}