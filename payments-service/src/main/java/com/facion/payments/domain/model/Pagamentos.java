package com.facion.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "pagamentos")
public class Pagamentos {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "chave_idempotencia", unique = true)
    private String chaveIdempotencia;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal valor;

    @Column(length = 3)
    private String moeda = "BRL";

    @Column(name = "pagador_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID pagadorId;

    @Column(name = "recebedor_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID recebedorId;

    @Column(name = "metodo_pagamento", nullable = false)
    private String metodoPagamento;

    @Column(nullable = false)
    private String status = "PENDENTE";

    @Column(name = "nome_banco")
    private String nomeBanco;

    @Column(name = "referencia_externa")
    private String referenciaExterna;

    @Version
    @Column(name = "versao")
    private Integer versao;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Column(name = "confirmado_em")
    private LocalDateTime confirmadoEm;

    @Column(name = "informacoes_adicionais", columnDefinition = "json")
    private String informacoesAdicionais;

    public Pagamentos() {
    }

    // Ciclo de vida para auditoria
    @PrePersist
    protected void aoCriar() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.atualizadoEm = LocalDateTime.now();
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChaveIdempotencia() {
        return chaveIdempotencia;
    }

    public void setChaveIdempotencia(String chaveIdempotencia) {
        this.chaveIdempotencia = chaveIdempotencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public UUID getPagadorId() {
        return pagadorId;
    }

    public void setPagadorId(UUID pagadorId) {
        this.pagadorId = pagadorId;
    }

    public UUID getRecebedorId() {
        return recebedorId;
    }

    public void setRecebedorId(UUID recebedorId) {
        this.recebedorId = recebedorId;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getReferenciaExterna() {
        return referenciaExterna;
    }

    public void setReferenciaExterna(String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }

    public Integer getVersao() {
        return versao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public LocalDateTime getConfirmadoEm() {
        return confirmadoEm;
    }

    public void setConfirmadoEm(LocalDateTime confirmadoEm) {
        this.confirmadoEm = confirmadoEm;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    // Equals e HashCode (Baseados no ID)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pagamentos that = (Pagamentos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
