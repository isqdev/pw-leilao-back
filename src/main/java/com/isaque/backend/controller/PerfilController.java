package com.isaque.backend.controller;

import java.util.List;

import com.isaque.backend.model.Perfil;
import com.isaque.backend.service.PerfilService;
import org.apache.catalina.connector.Response;
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
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<Page<Perfil>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(perfilService.buscarTodos(pageable));
    }

    @PostMapping("/{perfil}")
    public ResponseEntity<Perfil> inserir(@PathVariable("perfil") String perfil) {
        return ResponseEntity.ok(perfilService.inserir(perfil));
    }

    @PutMapping
    public ResponseEntity<Perfil> alterar(@Valid @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.alterar(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        perfilService.excluir(id);
        return ResponseEntity.ok("Excluído");
    }
}
