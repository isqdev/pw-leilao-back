package com.isaque.backend.service;

import com.isaque.backend.exception.NaoEncontradoExcecao;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MessageSource messageSource;

    public Categoria inserir(Categoria categoria) {
        Categoria categoriaCadastrado = categoriaRepository.save(categoria);
        return categoriaCadastrado;
    }

    public Categoria alterar(Categoria categoria) {
        Categoria categoriaBanco = categoriaRepository.findById(categoria.getId())
                .orElseThrow(
                        () -> new NaoEncontradoExcecao(messageSource.getMessage(
                                "categoria.notfound",
                                new Object[] { categoria.getId() },
                                LocaleContextHolder.getLocale())));
        categoriaBanco.setNome(categoria.getNome());
        return categoriaRepository.save(categoriaBanco);
    }

    public void excluir(Long id) {
        Categoria categoriaBanco= buscarPorId(id);
        categoriaRepository.delete(categoriaBanco);
    }

    public Categoria buscarPorId(Long id) {
        Categoria categoriaBanco = categoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao
                        (messageSource.getMessage("categoria.notfound",
                                new Object[] { id }, LocaleContextHolder.getLocale())));
        return categoriaRepository.save(categoriaBanco);
    }

    public Page<Categoria> buscarTodos(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public List<Categoria> buscarUltimos4() {
        return categoriaRepository.findTop4ByOrderByIdDesc();
    }
}
