package com.gestfit.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Recepcionista extends Funcionario {


    public Recepcionista() { }

   public Recepcionista(String nome, String cpf, String matricula ,String telefone, String email, LocalDate dataNascimento , String cargo, double salario, LocalDate dataAdmissao ,String cargoHorario, String turno) {
        super(nome, cpf, matricula ,telefone, email, dataNascimento , cargo, salario, dataAdmissao ,cargoHorario, turno);
    }
}
