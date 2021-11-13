package br.org.generation.squadspringgames.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O atributo usuario é obrigatório!")
    @Email(message = "O atributo deve ser um válido!")
    private String usuario;

    @NotBlank(message = "O atributo senha é obrigatório")
    @Size(min = 8, message = "a senha deve conter pelomenos 8 caracteres")
    private String senha;

    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "O atributo data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
