package com.gestfit.model;


import java.time.LocalDate;
import jakarta.persistence.Entity;
@Entity
public class Aluno extends Pessoa{


    protected String endereco;
    protected boolean ativo;

    public Aluno() { }

    public Aluno(long id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento, String endereco, boolean ativo) {
        super(id, nome, cpf, telefone, email, dataNascimento);
        this.endereco = endereco;
        this.ativo = ativo;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
