package com.gestfit.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDoPagamento statusDoPagamento;

    public Pagamento() {
        this.statusDoPagamento = StatusDoPagamento.PENDENTE;
    }

    public void registrarPagamento(FormaPagamento formaPagamento) {
        this.dataPagamento = LocalDate.now();
        this.formaPagamento = formaPagamento;
        this.statusDoPagamento = StatusDoPagamento.PAGO;
    }

    public void alterarStatus(StatusDoPagamento novoStatus){
        this.statusDoPagamento = novoStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusDoPagamento getStatusDoPagamento() {
        return statusDoPagamento;
    }

    public void setStatusDoPagamento(StatusDoPagamento statusDoPagamento) {
        this.statusDoPagamento = statusDoPagamento;
    }
}
