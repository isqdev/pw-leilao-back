package com.isaque.backend.service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

import com.isaque.backend.model.Perfil;
import com.isaque.backend.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isaque.backend.exception.NaoEncontradoExcecao;
import com.isaque.backend.exception.NegocioExcecao;
import com.isaque.backend.model.Perfil;
import com.isaque.backend.repository.PerfilRepository;
import org.thymeleaf.context.Context;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;

    public Perfil inserir(String perfil) {
        return perfilRepository.save(new Perfil(perfil));
    }

    public Perfil alterar(Perfil perfil) {
        Perfil perfilBanco = perfilRepository.findById(perfil.getId())
                .orElseThrow(
                        () -> new NaoEncontradoExcecao(messageSource.getMessage(
                                "perfil.notfound",
                                new Object[] { perfil.getId() },
                                LocaleContextHolder.getLocale())));
        perfilBanco.setNome(perfil.getNome());
        return perfilRepository.save(perfilBanco);
    }

    public void excluir(Long id) {
        Perfil perfilBanco= buscarPorId(id);
        perfilRepository.delete(perfilBanco);
    }

    public Perfil buscarPorId(Long id) {
        Perfil perfilBanco = perfilRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao
                        (messageSource.getMessage("perfil.notfound",
                                new Object[] { id }, LocaleContextHolder.getLocale())));
        return perfilRepository.save(perfilBanco);
    }

    public Page<Perfil> buscarTodos(Pageable pageable) {
        return perfilRepository.findAll(pageable);
    }
}
