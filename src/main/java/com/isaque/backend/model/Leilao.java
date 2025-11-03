package com.isaque.backend.model;
import com.isaque.backend.dto.request.LeilaoRequestDTO;
import com.isaque.backend.service.CategoriaService;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
    private String observacao;
    private Float valorIncremento;
    private Float lanceMinimo;
    @ManyToOne(optional = true)
    private Categoria categoria;
    @OneToMany
    private List<Imagem> imagem;
    @ManyToOne
    private Pessoa realizador;

    public Leilao(String titulo, String descricao, String descricaoDetalhada, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, StatusLeilao status, String observacao, Float valorIncremento, Float lanceMinimo, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.descricaoDetalhada = descricaoDetalhada;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.status = status;
        this.observacao = observacao;
        this.valorIncremento = valorIncremento;
        this.lanceMinimo = lanceMinimo;
        this.categoria = categoria;
    }

    public Leilao() {}

    public enum StatusLeilao {
        ABERTO,
        ENCERRADO,
        CANCELADO,
        EM_ANALISE;
    }

    public void atualizar (LeilaoRequestDTO leilao) {
        this.titulo = leilao.getTitulo();
        this.descricao = leilao.getDescricao();
        this.descricaoDetalhada = leilao.getDescricaoDetalhada();
        this.dataHoraInicio = leilao.getDataHoraInicio();
        this.dataHoraFim = leilao.getDataHoraFim();
        this.lanceMinimo = leilao.getLanceMinimo();
        this.valorIncremento = leilao.getValorIncremento();
        this.observacao = leilao.getObservacao();

    }

}
