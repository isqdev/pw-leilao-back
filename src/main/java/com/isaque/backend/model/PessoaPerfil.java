package com.isaque.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "pessoa_perfil")
@Data

public class PessoaPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @JsonIgnore
    private Pessoa pessoa;

    public PessoaPerfil(Pessoa pessoa, Perfil perfil) {
        this.perfil = perfil;
        this.pessoa = pessoa;
    }

    public PessoaPerfil() {}
}
