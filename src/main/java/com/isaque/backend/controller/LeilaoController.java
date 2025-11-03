package com.isaque.backend.controller;

import com.isaque.backend.dto.request.LeilaoRequestDTO;
import com.isaque.backend.dto.response.LeilaoResponsePreviewDTO;
import com.isaque.backend.model.Leilao;
import com.isaque.backend.service.LeilaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leilao")
public class LeilaoController {
    @Autowired
    private LeilaoService leilaoService;

    @GetMapping
    public ResponseEntity<Page<Leilao>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(leilaoService.buscarTodos(pageable));
    }

    @GetMapping("/preview")
    public ResponseEntity<Page<LeilaoResponsePreviewDTO>> buscarTodosPreview(Pageable pageable) {
        return ResponseEntity.ok(leilaoService.buscarTodosPreview(pageable));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<Leilao>> buscarPorCategoria(@PathVariable("id") Long id, Pageable pageable) {
        return ResponseEntity.ok(leilaoService.buscarPorCategoria(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leilaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Leilao> inserir(@Valid @RequestBody LeilaoRequestDTO dto) {
        return ResponseEntity.ok(leilaoService.inserir(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leilao> alterar(@Valid @RequestBody LeilaoRequestDTO dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(leilaoService.alterar(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        leilaoService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
