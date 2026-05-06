package com.gestfit.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected long id;

   protected String nome;
   protected String cpf;
   protected String telefone;
   protected String email;
   protected LocalDate dataNascimento;

   public Pessoa() { }

    public Pessoa (long id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
