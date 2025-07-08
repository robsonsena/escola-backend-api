package com.gestaoescolar.service;

import com.gestaoescolar.dto.MatriculaDTO;
import com.gestaoescolar.exception.ResourceNotFoundException;
import com.gestaoescolar.model.Aluno;
import com.gestaoescolar.model.Curso;
import com.gestaoescolar.model.Matricula;
import com.gestaoescolar.repository.AlunoRepository;
import com.gestaoescolar.repository.CursoRepository;
import com.gestaoescolar.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Matricula create(MatriculaDTO matriculaDTO) {
        Aluno aluno = alunoRepository.findById(matriculaDTO.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + matriculaDTO.getAlunoId()));

        Curso curso = cursoRepository.findById(matriculaDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + matriculaDTO.getCursoId()));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        return matriculaRepository.save(matricula);
    }
    /**
     * Encontra todos os cursos em que um aluno específico está matriculado.
     * @param alunoId O ID do aluno.
     * @return Uma lista de Cursos.
     * @throws ResourceNotFoundException se o aluno não for encontrado.
     */
    @Transactional(readOnly = true)
    public List<Curso> findCursosByAlunoId(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + alunoId));

        return aluno.getMatriculas().stream()
                .map(Matricula::getCurso)
                .collect(Collectors.toList());
    }
}












