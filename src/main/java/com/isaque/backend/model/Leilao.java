package com.isaque.backend.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private String descricaoDetalhada;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusLeilao status;
    private String obervacao;
    private Float valorIncremento;
    private Float lanceMinimo;
    @OneToOne
    private Categoria categoria;
    @OneToMany
    private List<Imagem> imagem;
    @ManyToOne
    private Pessoa realizador;

    private enum StatusLeilao {
        ABERTO,
        ENCERRADO,
        CANCELADO,
        EM_ANALISE;
    }

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

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public StatusLeilao getStatus() {
        return status;
    }

    public void setStatus(StatusLeilao status) {
        this.status = status;
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

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

    public Pessoa getRealizador() {
        return realizador;
    }

    public void setRealizador(Pessoa realizador) {
        this.realizador = realizador;
    }
}
