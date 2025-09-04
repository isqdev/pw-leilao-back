package com.isaque.backend.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.context.request.WebRequest;

import lombok.Data;

@Data
public class RespostaErro {
    private int codigo;
    private LocalDate dataHora;
    private String erro;
    private String mensagem;
    private String caminho;
    private List<String> detalhes;

    public RespostaErro(int codigo, String erro, String mensagem, String caminho, List<String> detalhes) {
        dataHora = LocalDate.now();
        this.codigo = codigo;
        this.erro = erro;
        this.caminho = caminho;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

}
