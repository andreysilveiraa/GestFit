package com.gestfit.model;

import java.time.LocalDate;

public class Aluno extends Pessoa{

    protected LocalDate dataNascimento;
    protected String endereco;
    protected boolean ativo;

    public Aluno() { }

    public Aluno(long id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento, String endereco, boolean ativo) {
        super(id, nome, cpf, telefone, email);
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
