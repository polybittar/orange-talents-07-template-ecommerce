package br.com.zupacademy.polyana.mercadolivre.domain.usuario;

import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;

@Entity
public class Perfil implements GrantedAuthority {


    private static final long serialVersionUID = 1L;    //não é obrigatório mas geramos para parar de dar warning
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
