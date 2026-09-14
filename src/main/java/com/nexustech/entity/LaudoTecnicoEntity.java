package com.nexustech.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_laudos_tecnicos")
public class LaudoTecnicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String equipamento;

    @Column(name = "resumo_falha", nullable = false, length = 2000)
    private String resumoFalha;

    @Column(nullable = false, length = 20)
    private String gravidade;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_laudo_acoes",
            joinColumns = @JoinColumn(name = "laudo_id")
    )
    @OrderColumn(name = "ordem")
    @Column(name = "acao", nullable = false, length = 1000)
    private List<String> acoesRecomendadas = new ArrayList<>();

    @Column(name = "parar_producao", nullable = false)
    private boolean pararProducao;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public LaudoTecnicoEntity() {
    }

    @PrePersist
    public void preencherDataCriacao() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getResumoFalha() {
        return resumoFalha;
    }

    public void setResumoFalha(String resumoFalha) {
        this.resumoFalha = resumoFalha;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public List<String> getAcoesRecomendadas() {
        return acoesRecomendadas;
    }

    public void setAcoesRecomendadas(
            List<String> acoesRecomendadas
    ) {
        this.acoesRecomendadas =
                acoesRecomendadas == null
                        ? new ArrayList<>()
                        : new ArrayList<>(acoesRecomendadas);
    }

    public boolean isPararProducao() {
        return pararProducao;
    }

    public void setPararProducao(boolean pararProducao) {
        this.pararProducao = pararProducao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}