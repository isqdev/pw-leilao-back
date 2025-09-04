package com.isaque.backend.service;

import com.isaque.backend.dto.PessoaLoginDTO;
import com.isaque.backend.dto.PessoaRequestDTO;
import com.isaque.backend.model.Pessoa;
import com.isaque.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.isaque.backend.security.JwtService;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private JwtService jwtService;

    public PessoaLoginDTO autenticar(PessoaRequestDTO pessoa) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
        );

        Pessoa pessoaBanco = pessoaRepository.findByEmail(pessoa.getEmail()).get();

        PessoaLoginDTO loginDTO = new PessoaLoginDTO();
        loginDTO.setEmail(pessoaBanco.getEmail());
        loginDTO.setNome(pessoaBanco.getNome());
        loginDTO.setToken(jwtService.generateToken(authentication.getName()));

        return loginDTO;
    }
}

