package com.isaque.backend.repository;

import com.isaque.backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findTop4ByOrderByIdDesc();

}
