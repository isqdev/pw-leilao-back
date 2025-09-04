package com.isaque.backend.repository;

import com.isaque.backend.model.Categoria;
import com.isaque.backend.model.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeilaoRepository extends  JpaRepository<Leilao, Long> {
    List<Leilao> findByCategoria(Categoria categoria);
}
