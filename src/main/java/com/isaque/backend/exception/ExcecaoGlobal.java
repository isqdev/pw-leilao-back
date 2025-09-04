package com.isaque.backend.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExcecaoGlobal {

    @ExceptionHandler(NaoEncontradoExcecao.class)
    public ResponseEntity<RespostaErro> naoEncontrado (NaoEncontradoExcecao ex, WebRequest request) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.NOT_FOUND.value(), "Não encontrado", ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegocioExcecao.class)
    public ResponseEntity<RespostaErro> negocio (NegocioExcecao ex, WebRequest request) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de negócio", ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErro> validacao(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());

        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST.value(), "Erro de validação", "Campos Inválidos", request.getDescription(false), erros);

        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> global (Exception ex, WebRequest request) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST.value(), "Erro interno", ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
