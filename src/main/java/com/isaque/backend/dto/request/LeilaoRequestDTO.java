package com.isaque.backend.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeilaoRequestDTO {
    private String titulo;
    private String descricao;
    private String descricaoDetalhada;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String observacao;
    private Float valorIncremento;
    private Float lanceMinimo;
    private Long categoria;
}
