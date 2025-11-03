package com.isaque.backend.service;

import com.isaque.backend.dto.request.LeilaoRequestDTO;
import com.isaque.backend.dto.response.LeilaoResponseMaxDTO;
import com.isaque.backend.dto.response.LeilaoResponsePreviewDTO;
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

@Service
public class LeilaoService {
    @Autowired
    LeilaoRepository leilaoRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoriaService categoriaService;

    public Leilao inserir(LeilaoRequestDTO dto) {
        Categoria categoria = categoriaService.buscarPorId(dto.getCategoria());

        Leilao leilao = new Leilao(
                dto.getTitulo(), dto.getDescricao(), dto.getDescricaoDetalhada(),
                dto.getDataHoraInicio(), dto.getDataHoraFim(), Leilao.StatusLeilao.EM_ANALISE,
                dto.getObservacao(), dto.getValorIncremento(), dto.getLanceMinimo(), categoria);

        return leilaoRepository.save(leilao);
    }

    public Leilao alterar(LeilaoRequestDTO leilao, Long id) {
        Leilao leilaoBanco = leilaoRepository.findById(id)
                .orElseThrow(
                        () -> new NaoEncontradoExcecao(messageSource.getMessage(
                                "leilao.notfound",
                                new Object[] { id },
                                LocaleContextHolder.getLocale())));

        leilaoBanco.atualizar(leilao);
        leilaoBanco.setCategoria(categoriaService.buscarPorId(leilao.getCategoria()));

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

    public Page<LeilaoResponsePreviewDTO> buscarTodosPreview(Pageable pageable) {
        return leilaoRepository.findAll(pageable)
                .map(leilao -> new LeilaoResponsePreviewDTO(leilao.getId(), leilao.getTitulo(), leilao.getDescricao(), leilao.getDataHoraInicio()));
    }

    public LeilaoResponseMaxDTO buscarLeilaoCompleto(Long id) {
        LeilaoResponseMaxDTO dto = new LeilaoResponseMaxDTO();
        Leilao leilao = buscarPorId(id);
        return dto;
    }

    public Page<Leilao> buscarPorCategoria(Long id, Pageable pageable) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return leilaoRepository.findByCategoria(categoria, pageable);
    }

//    public Page<Leilao> buscarPorStatus(Long id, Pageable pageable) {
////        Categoria categoria = sta.buscarPorId(id);
////        return leilaoRepository.findByCategoria(categoria, pageable);
//    }
}
