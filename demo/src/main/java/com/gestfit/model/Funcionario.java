package com.gestfit.model;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Funcionario extends Pessoa {

    protected String cargo;
    protected double salarioBase;
    protected LocalDate dataAdmissao;
    protected String cargoHorario;
    protected String turno;
    public Funcionario() { }

    public Funcionario(String nome, String cpf, String matricula ,String telefone, String email, LocalDate dataNascimento , String cargo, double salario, LocalDate dataAdmissao ,String cargoHorario, String turno) {
        super(nome, cpf, matricula ,telefone, email, dataNascimento);
        this.cargo = cargo;
        this.salarioBase = salario;
        this.dataAdmissao = dataAdmissao;
        this.cargoHorario = cargoHorario;
        this.turno = turno;
    }

    public Funcionario(String nome, String cpf, String matricula){
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula ;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salario) {
        this.salarioBase = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCargoHorario() {
        return cargoHorario;
    }

    public void setCargoHorario(String cargoHorario) {
        this.cargoHorario = cargoHorario;
    }

}
