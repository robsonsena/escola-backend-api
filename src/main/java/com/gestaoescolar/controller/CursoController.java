package com.gestaoescolar.controller;

import com.gestaoescolar.dto.CursoDTO;
import com.gestaoescolar.model.Curso;
import com.gestaoescolar.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    private CursoDTO toDto(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setCodigo(curso.getCodigo());
        dto.setDescricao(curso.getDescricao());
        return dto;
    }

    private Curso toEntity(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setCodigo(dto.getCodigo());
        curso.setDescricao(dto.getDescricao());
        return curso;
    }

    @GetMapping
    public List<CursoDTO> getAllCursos() {
        return cursoService.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/codigo/{codigo}")
    public CursoDTO getCursoByCodigo(@PathVariable String codigo) {
        return toDto(cursoService.findByCodigo(codigo));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDTO createCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        Curso curso = toEntity(cursoDTO);
        return toDto(cursoService.create(curso));
    }

    @PutMapping("/codigo/{codigo}")
    public CursoDTO updateCurso(@PathVariable String codigo, @Valid @RequestBody CursoDTO cursoDTO) {
        Curso curso = toEntity(cursoDTO);
        return toDto(cursoService.update(codigo, curso));
    }
}

