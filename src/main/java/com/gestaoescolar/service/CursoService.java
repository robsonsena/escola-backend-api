package com.gestaoescolar.service;

import com.gestaoescolar.exception.ResourceNotFoundException;
import com.gestaoescolar.model.Curso;
import com.gestaoescolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findByCodigo(String codigo) {
        return cursoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com código: " + codigo));
    }

    public Curso create(Curso curso) {
        // Poderia adicionar validação de código duplicado aqui
        return cursoRepository.save(curso);
    }

    public Curso update(String codigo, Curso cursoDetails) {
        Curso curso = findByCodigo(codigo);

        if (cursoDetails.getNome() != null) {
            curso.setNome(cursoDetails.getNome());
        }
        if (cursoDetails.getCodigo() != null) {
            curso.setCodigo(cursoDetails.getCodigo());
        }
        if (cursoDetails.getDescricao() != null) {
            curso.setDescricao(cursoDetails.getDescricao());
        }

        return cursoRepository.save(curso);
    }

}


