package com.gestfit.model;

public class Funcionario extends Pessoa {

    protected String cargo;

    protected double salarioBase;

    public Funcionario() { }

    public Funcionario(long id, String nome, String cpf, String telefone, String email, String cargo, double salario) {
        super(id, nome, cpf, telefone, email);
        this.cargo = cargo;
        this.salarioBase = salario;
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
