package com.gestfit.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Professor extends Funcionario {

    protected String especialidade;


    public Professor() { }

    public Professor(String nome, String cpf, String matricula ,String telefone, String email, LocalDate dataNascimento , String cargo, double salario, LocalDate dataAdmissao ,String cargoHorario, String turno, String especialidade) {
        super(nome, cpf, matricula ,telefone, email, dataNascimento , cargo, salario, dataAdmissao ,cargoHorario, turno);
        this.especialidade = especialidade;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
