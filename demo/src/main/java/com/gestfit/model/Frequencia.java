package com.gestfit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraEntrada;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}