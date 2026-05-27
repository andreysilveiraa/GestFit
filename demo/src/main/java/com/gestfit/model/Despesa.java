package com.gestfit.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaDaDespesa categoria;

    public Despesa () {

    }

    public Despesa (String descricao, Double valor, LocalDate dataVencimento, CategoriaDaDespesa categoria){
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public CategoriaDaDespesa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDaDespesa categoria) {
        this.categoria = categoria;
    }
}
