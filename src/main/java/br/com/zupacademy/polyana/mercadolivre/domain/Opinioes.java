package br.com.zupacademy.polyana.mercadolivre.domain;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {                 //operações, divisão da responsabilidade

    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public double media() {
        Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }

    public int total() {
        return this.opinioes.size();
    }

}
