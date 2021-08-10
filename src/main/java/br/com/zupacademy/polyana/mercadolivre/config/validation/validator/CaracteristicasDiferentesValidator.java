package br.com.zupacademy.polyana.mercadolivre.config.validation.validator;

import br.com.zupacademy.polyana.mercadolivre.dto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class CaracteristicasDiferentesValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoRequest form = (ProdutoRequest) target;
        Set<String> nomesRepetidos = form.buscaCaracteristicasIguais();
        if(!nomesRepetidos.isEmpty()){
            errors.rejectValue("caracteristicas",null, "Caracteristicas repetidas"+nomesRepetidos);
        }
    }
}