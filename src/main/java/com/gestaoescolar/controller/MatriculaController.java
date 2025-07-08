package com.gestaoescolar.controller;

import com.gestaoescolar.dto.CursoDTO;
import com.gestaoescolar.dto.MatriculaDTO;
import com.gestaoescolar.dto.MatriculaResponseDTO;
import com.gestaoescolar.model.Curso;
import com.gestaoescolar.model.Matricula;
import com.gestaoescolar.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    private MatriculaResponseDTO toResponseDto(Matricula matricula) {
        MatriculaResponseDTO dto = new MatriculaResponseDTO();
        dto.setId(matricula.getId());
        dto.setAlunoId(matricula.getAluno().getId());
        dto.setCursoId(matricula.getCurso().getId());
        return dto;
    }

    private CursoDTO toCursoDto(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setCodigo(curso.getCodigo());
        dto.setDescricao(curso.getDescricao());
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponseDTO createMatricula(@Valid @RequestBody MatriculaDTO matriculaDTO) {
        Matricula matricula = matriculaService.create(matriculaDTO);
        return toResponseDto(matricula);
    }

    @GetMapping("/aluno/{alunoId}/cursos")
    public List<CursoDTO> getCursosByAluno(@PathVariable Long alunoId) {
        return matriculaService.findCursosByAlunoId(alunoId)
                .stream()
                .map(this::toCursoDto)
                .collect(Collectors.toList());
    }
}

