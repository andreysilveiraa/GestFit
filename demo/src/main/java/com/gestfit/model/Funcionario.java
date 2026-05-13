package com.gestfit.model;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Funcionario extends Pessoa {

    protected String cargo;

    protected double salarioBase;

    public Funcionario() { }

    public Funcionario(String nome, String cpf, String matricula ,String telefone, String email, LocalDate dataNascimento , String cargo, double salario) {
        super(nome, cpf, matricula ,telefone, email, dataNascimento);
        this.cargo = cargo;
        this.salarioBase = salario;
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

}
