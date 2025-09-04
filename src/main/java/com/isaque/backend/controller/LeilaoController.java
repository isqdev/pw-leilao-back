package com.isaque.backend.controller;

import com.isaque.backend.dto.CategoriaDTO;
import com.isaque.backend.dto.LeilaoRequestMiniDTO;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.model.Leilao;
import com.isaque.backend.service.CategoriaService;
import com.isaque.backend.service.LeilaoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leilao")
public class LeilaoController {
    @Autowired
    private LeilaoService leilaoService;

    @GetMapping
    public ResponseEntity<Page<Leilao>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(leilaoService.buscarTodos(pageable));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Leilao>> buscarPorCategoria(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leilaoService.buscarPorCategoria(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(leilaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Leilao> inserir(@Valid @RequestBody LeilaoRequestMiniDTO leilaoDTO) {
        Leilao leilao = new Leilao();
        leilao.setTitulo(leilaoDTO.getTitulo());
        leilao.setDescricao(leilaoDTO.getDescricao());
        leilao.setDataHoraInicio(leilaoDTO.getDataHoraInicio());

        return ResponseEntity.ok(leilaoService.inserir(leilao));
    }

    @PutMapping
    public ResponseEntity<Leilao> alterar(@Valid @RequestBody Leilao leilao) {
        return ResponseEntity.ok(leilaoService.alterar(leilao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        leilaoService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
