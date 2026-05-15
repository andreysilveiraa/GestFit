package com.gestfit.model;

import jakarta.persistence.*;

@Entity
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tipos tipoDoPlano;

    private double valor;
    private int duracaoMeses;
    private String descricao;
    private boolean ativo = true;

    public Plano() {}

    public Plano(Tipos tipo, double valor, int duracao, String descricao) {
        this.tipoDoPlano = tipo;
        this.valor = valor;
        this.duracaoMeses = duracao;
        this.descricao = descricao;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tipos getTipoDoPlano(){
        return tipoDoPlano;
    }

    public void setTipoDoPlano(Tipos tipoDoPlano) {
        this.tipoDoPlano = tipoDoPlano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
