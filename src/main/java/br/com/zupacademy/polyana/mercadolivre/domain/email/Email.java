package br.com.zupacademy.polyana.mercadolivre.domain.email;

import br.com.zupacademy.polyana.mercadolivre.domain.compra.Compra;
import br.com.zupacademy.polyana.mercadolivre.domain.produto.Pergunta;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Email {

    private EnviaEmail enviaEmail;

    public Email(EnviaEmail enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    public void pergunta(@Valid @NotNull Pergunta pergunta) {
        enviaEmail.enviar("Você recebeu uma nova pergunta:",pergunta.getTitulo(),pergunta.getUsuario().getLogin(),
                "novapergunta@mercadolivre.com",pergunta.getProduto().getUsuario().getLogin());
    }

    public void novaCompra(@Valid @NotNull Compra compra){
        enviaEmail.enviar("Nova compra do produto:" , compra.getProduto().getNome(), compra.getUsuario().getLogin(),
                "novacompra@mercadolivre.com",compra.getProduto().getUsuario().getLogin());    }
}
