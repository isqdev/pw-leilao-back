package com.isaque.backend.service;

import com.isaque.backend.dto.request.FeedbackRequestDTO;
import com.isaque.backend.model.Categoria;
import com.isaque.backend.model.Feedback;
import com.isaque.backend.repository.FeedbackRepository;
import com.isaque.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PessoaService pessoaService;

    public Page<Feedback> buscarTodos(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    public Feedback inserir(FeedbackRequestDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setComentario(dto.getComentario());
        feedback.setNota(dto.getNota());
        feedback.setPessoa(pessoaService.buscarPorId(dto.getIdPessoa()));

        return feedbackRepository.save(feedback);
    }

}
