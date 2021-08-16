package br.com.zupacademy.polyana.mercadolivre.domain;

import br.com.zupacademy.polyana.mercadolivre.dto.CaracteristicaProdutoRequest;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;
    private@NotNull int quantidade;
    private @NotBlank @Length(max = 1000) String descricao;
    private @NotNull @Positive BigDecimal valor;
    private LocalDateTime data;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @NotNull
    @Valid
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    //cascade persist: toda vez que cadastrar um novo produto, cadastra tbm as caracteristicas
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)           //cascade merge: atualiza ambos
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, int quantidade, BigDecimal valor, String descricao,
                   Categoria categoria, LocalDateTime data, Usuario usuario,
                   @Valid Collection<CaracteristicaProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(
                caracteristica -> caracteristica.converter(this)).collect(Collectors.toSet()));         //converte cada caracteristica
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto tem de ter mais de 3 características");      //invariancia

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }


    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.usuario.getLogin().equals(possivelDono.getLogin());
    }


    public <T> Set<T> mapeiaCaracteristicas(
            Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora)
                .collect(Collectors.toCollection(TreeSet::new));          //o treeset ordena as perguntas
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }


    public boolean diminuiEstoque(int quantidade) {
        Assert.isTrue(quantidade > 0,"A quantidade deve ser maior que zero");
        if(quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        }else{
            return false;
        }
    }
}