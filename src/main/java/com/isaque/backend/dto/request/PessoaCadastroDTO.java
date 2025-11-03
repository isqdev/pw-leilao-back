package com.isaque.backend.dto.request;

import lombok.Data;

@Data
public class PessoaCadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private Long perfilId;
}