package com.gestfit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tb_recepcionista")
public class Recepcionista extends Funcionario {

    private String idioma; // Atributo opcional de exemplo ou controle interno da recepção

    public Recepcionista() {}

    public Recepcionista(String nome, String cpf, String matricula, String telefone, String email, LocalDate dataNascimento,
                         String cargo, double salario, LocalDate dataAdmissao, String cargoHorario, String turno) {
        super(nome, cpf, matricula, telefone, email, dataNascimento, cargo, salario, dataAdmissao, cargoHorario, turno);
    }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
}