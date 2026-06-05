package com.gestfit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_log_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String usuarioResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOperacao operacao;

    @Column(columnDefinition = "TEXT")
    private String detalhes;

    // Construtor Padrão (Obrigatório para o JPA)
    public LogAuditoria() {
    }

    // Construtor Auxiliar para facilitar a criação dos logs
    public LogAuditoria(String usuarioResponsavel, TipoOperacao operacao, String detalhes) {
        this.timestamp = LocalDateTime.now();
        this.usuarioResponsavel = usuarioResponsavel;
        this.operacao = operacao;
        this.detalhes = detalhes;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public void setOperacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}