package com.gestaoescolar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatriculaDTO {
    @NotNull
    private Long alunoId;
    @NotNull
    private Long cursoId;
}

