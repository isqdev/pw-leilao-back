package com.isaque.backend.controller;

import com.isaque.backend.dto.request.CategoriaRequestDTO;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setObservacao(categoriaDTO.getObservacao());

        return ResponseEntity.ok(categoriaService.inserir(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> alterar(@Valid @RequestBody CategoriaRequestDTO categoria, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.alterar(categoria, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
