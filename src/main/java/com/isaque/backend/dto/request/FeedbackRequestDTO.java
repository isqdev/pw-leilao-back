package com.isaque.backend.dto.request;

import com.isaque.backend.model.Pessoa;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackRequestDTO {
    private String comentario;
    private Integer nota;
    private Long idPessoa;
}
