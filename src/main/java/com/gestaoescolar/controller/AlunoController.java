package com.gestaoescolar.controller;

import com.gestaoescolar.dto.AlunoDTO;
import com.gestaoescolar.model.Aluno;
import com.gestaoescolar.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // Mapper de Entidade para DTO
    private AlunoDTO toDto(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setTelefone(aluno.getTelefone());
        return dto;
    }

    // Mapper de DTO para Entidade
    private Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setTelefone(dto.getTelefone());
        return aluno;
    }

    @GetMapping
    public List<AlunoDTO> getAllAlunos() {
        return alunoService.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AlunoDTO getAlunoById(@PathVariable Long id) {
        return toDto(alunoService.findById(id));
    }

    @GetMapping("/nome/{nome}")
    public List<AlunoDTO> getAlunosByNome(@PathVariable String nome) {
        return alunoService.findByNome(nome).stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/email/{email}")
    public AlunoDTO getAlunoByEmail(@PathVariable String email) {
        return toDto(alunoService.findByEmail(email));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoDTO createAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = toEntity(alunoDTO);
        return toDto(alunoService.create(aluno));
    }

    @PutMapping("/{id}")
    public AlunoDTO updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = toEntity(alunoDTO);
        return toDto(alunoService.update(id, aluno));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);
    }
}

