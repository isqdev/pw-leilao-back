package com.isaque.backend.controller;

import com.isaque.backend.dto.PessoaLoginDTO;
import com.isaque.backend.dto.PessoaRequestDTO;
import com.isaque.backend.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
        @Autowired
        private AutenticacaoService autenticacaoService;

        @PostMapping("/login")
        public ResponseEntity<PessoaLoginDTO> login(@RequestBody PessoaRequestDTO pessoa) {
            return ResponseEntity.ok(autenticacaoService.autenticar(pessoa));
        }
};
