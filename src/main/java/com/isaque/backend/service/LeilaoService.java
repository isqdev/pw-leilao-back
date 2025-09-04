package com.isaque.backend.service;

import com.isaque.backend.exception.NaoEncontradoExcecao;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.model.Leilao;
import com.isaque.backend.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeilaoService {
    @Autowired
    LeilaoRepository leilaoRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoriaService categoriaService;

    public Leilao inserir(Leilao leilao) {
        Leilao leilaoCadastrado = leilaoRepository.save(leilao);
        return leilaoCadastrado;
    }

    public Leilao alterar(Leilao leilao) {
        Leilao leilaoBanco = leilaoRepository.findById(leilao.getId())
                .orElseThrow(
                        () -> new NaoEncontradoExcecao(messageSource.getMessage(
                                "leilao.notfound",
                                new Object[] { leilao.getId() },
                                LocaleContextHolder.getLocale())));
        leilaoBanco.setTitulo(leilao.getTitulo());
        leilaoBanco.setDescricao(leilao.getDescricao());
        leilaoBanco.setDataHoraInicio(leilao.getDataHoraInicio());
        return leilaoRepository.save(leilaoBanco);
    }

    public void excluir(Long id) {
        Leilao leilaoBanco= buscarPorId(id);
        leilaoRepository.delete(leilaoBanco);
    }

    public Leilao buscarPorId(Long id) {
        Leilao leilaoBanco = leilaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao
                        (messageSource.getMessage("leilao.notfound",
                                new Object[] { id }, LocaleContextHolder.getLocale())));
        return leilaoRepository.save(leilaoBanco);
    }

    public Page<Leilao> buscarTodos(Pageable pageable) {
        return leilaoRepository.findAll(pageable);
    }

    public List<Leilao> buscarPorCategoria(Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return leilaoRepository.findByCategoria(categoria);
    }
}
