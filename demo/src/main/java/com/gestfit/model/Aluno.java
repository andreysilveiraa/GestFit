package com.gestfit.model;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Aluno extends Pessoa{


    protected String endereco;
    protected boolean ativo;

    @Enumerated(EnumType.STRING)
    protected StatusMatricula statusMatricula;

    @Enumerated(EnumType.STRING)
    protected Tipos tipoPlano;

    public Aluno() { }

    public Aluno(String nome, String cpf, String matricula){ // Isto é para teste
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula ;
        this.ativo = true;
        this.statusMatricula = StatusMatricula.ATIVA;
    }


    public Aluno(String nome, String cpf, String matricula, String telefone, String email,
                 LocalDate dataNascimento, String endereco, boolean ativo) {
        super(nome, cpf, matricula, telefone, email, dataNascimento);
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Tipos getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(Tipos tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public StatusMatricula getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(StatusMatricula statusMatricula) {
        this.statusMatricula = statusMatricula;
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
