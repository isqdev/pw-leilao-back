package com.isaque.backend.repository;

import com.isaque.backend.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isaque.backend.model.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {


}
