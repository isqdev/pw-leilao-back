package com.isaque.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comentario;
    private Integer nota;
    private LocalDateTime dataHora = LocalDateTime.now();

    @OneToOne
    private Pessoa pessoa;
}
