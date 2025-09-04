package com.isaque.backend.dto;

import com.isaque.backend.model.Categoria;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeilaoRequestMiniDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraInicio;

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
}
