package com.isaque.backend.controller;

import com.isaque.backend.dto.CategoriaDTO;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.buscarTodos(pageable));
    }

    @GetMapping("/only4")
    public List<Categoria> buscarUltimos4() {
        return categoriaService.buscarUltimos4();
    }

    @PostMapping
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setObservacao(categoriaDTO.getObservacao());

        return ResponseEntity.ok(categoriaService.inserir(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> alterar(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.alterar(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
