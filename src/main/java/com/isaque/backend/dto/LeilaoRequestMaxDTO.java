package com.isaque.backend.dto;

import com.isaque.backend.model.Categoria;
import com.isaque.backend.model.Leilao;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeilaoRequestMaxDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String descricaoDetalhada;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String obervacao;
    private Float valorIncremento;
    private Float lanceMinimo;
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getObervacao() {
        return obervacao;
    }

    public void setObervacao(String obervacao) {
        this.obervacao = obervacao;
    }

    public Float getValorIncremento() {
        return valorIncremento;
    }

    public void setValorIncremento(Float valorIncremento) {
        this.valorIncremento = valorIncremento;
    }

    public Float getLanceMinimo() {
        return lanceMinimo;
    }

    public void setLanceMinimo(Float lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
