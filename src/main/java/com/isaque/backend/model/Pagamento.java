package com.isaque.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float valor;
    private LocalDateTime dataHora;
    private String status;

    @OneToOne
    private Leilao leilao;
}
