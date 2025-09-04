package com.isaque.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float valorLance;
    private LocalDateTime dataHora;
    @OneToMany
    private List<Lance> lances;
    @ManyToOne
    private Pessoa lancador;
}
