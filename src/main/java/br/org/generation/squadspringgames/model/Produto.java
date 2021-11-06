package br.org.generation.squadspringgames.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "o atributo console é obrigatório")
    @Max(60)
    private String console;

    @NotNull(message = "O atributo descrição é obrigatório")
    @Size(min = 10, max = 500)
    private String descricao;
    @NotNull(message = "O atributo foto é obrigatório")
    @Size(min = 10, max = 500)
    private String foto;
    @NotNull(message = "o atributo nome é obrigatório")
    @Size(min = 5, max = 255)
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "O preço não pode estar vazio")
    @Positive(message = "o preço precisa ser maior que 0")
    private BigDecimal preco;

    private int quantidade;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
