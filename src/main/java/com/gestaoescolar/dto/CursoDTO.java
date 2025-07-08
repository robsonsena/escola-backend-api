package com.gestaoescolar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String codigo;
    private String descricao;
}

