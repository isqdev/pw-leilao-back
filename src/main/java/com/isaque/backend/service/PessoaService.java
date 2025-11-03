package com.isaque.backend.service;

import com.isaque.backend.dto.request.PessoaCadastroDTO;
import com.isaque.backend.model.Perfil;
import com.isaque.backend.model.PessoaPerfil;
import com.isaque.backend.repository.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isaque.backend.exception.NaoEncontradoExcecao;
import com.isaque.backend.model.Pessoa;
import com.isaque.backend.repository.PessoaRepository;
import org.thymeleaf.context.Context;

@Service
public class PessoaService implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PerfilService perfilService;

    public Pessoa cadastrarPessoa(@Valid PessoaCadastroDTO dto) {
        Perfil perfil = perfilService.buscarPorId(dto.getPerfilId());
        Pessoa pessoa = new Pessoa(dto.getNome(), dto.getEmail(), dto.getSenha(), perfil);

//        emailService.enviarEmailSimples(pessoaCadastro.getEmail(), "Cadastro com Sucesso", "Cadastro no Sistema de Leilão XXX foi feito com sucesso!");
        enviarEmailSucesso(pessoa);
        return pessoaRepository.save(pessoa);
    }

    private void enviarEmailSucesso(Pessoa pessoa) {
        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        emailService.emailTemplate(pessoa.getEmail(), "Cadastro sucesso", context, "cadastroSucesso");
    }

    public Pessoa alterar(Pessoa pessoa) {
        Pessoa pessoaBanco = pessoaRepository.findById(pessoa.getId())
                .orElseThrow(
                        () -> new NaoEncontradoExcecao(messageSource.getMessage(
                                "pessoa.notfound",
                                new Object[] { pessoa.getId() },
                                LocaleContextHolder.getLocale())));
        pessoaBanco.setNome(pessoa.getNome());
        pessoaBanco.setEmail(pessoa.getEmail());
        return pessoaRepository.save(pessoaBanco);
    }

    public void excluir(Long id) {
        Pessoa pessoaBanco = buscarPorId(id);
        pessoaRepository.delete(pessoaBanco);
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("pessoa.notfound",
                new Object[] { id },
                LocaleContextHolder.getLocale())));
    }

    public Page<Pessoa> buscarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Pessoa não encontrada"));
    }
}
