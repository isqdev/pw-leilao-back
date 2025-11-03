package com.isaque.backend.controller;

import com.isaque.backend.dto.request.PessoaCadastroDTO;
import com.isaque.backend.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaque.backend.model.Pessoa;
import com.isaque.backend.service.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<Page<Pessoa>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(pessoaService.buscarTodos(pageable));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody PessoaCadastroDTO dto) {
        return ResponseEntity.ok(pessoaService.cadastrarPessoa(dto));
    }

    @PutMapping
    public ResponseEntity<Pessoa> alterar(@Valid @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.alterar(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
