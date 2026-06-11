package com.gestfit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tb_gerente")
public class Gerente extends Funcionario {

    private String setor; // Ex: Administrativo, Financeiro, Operacional

    public Gerente() {}

    public Gerente(String nome, String cpf, String matricula, String telefone, String email, LocalDate dataNascimento,
                   String cargo, double salario, LocalDate dataAdmissao, String cargoHorario, String turno, String setor) {
        super(nome, cpf, matricula, telefone, email, dataNascimento, cargo, salario, dataAdmissao, cargoHorario, turno);
        this.setor = setor;
    }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}