package com.isaque.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String observacao;
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    private Pessoa criador;

}
