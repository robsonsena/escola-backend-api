package com.gestaoescolar.service;

import com.gestaoescolar.exception.ResourceNotFoundException;
import com.gestaoescolar.model.Aluno;
import com.gestaoescolar.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + id));
    }

    public List<Aluno> findByNome(String nome) {
        List<Aluno> alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        if (alunos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum aluno encontrado com o nome: " + nome);
        }
        return alunos;
    }

    public Aluno findByEmail(String email) {
        return alunoRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum aluno encontrado com o email: " + email));
    }

    public Aluno create(Aluno aluno) {
        // Poderia adicionar validação de email duplicado aqui
        return alunoRepository.save(aluno);
    }

    public Aluno update(Long id, Aluno alunoDetails) {
        Aluno aluno = findById(id);

        aluno.setNome(alunoDetails.getNome());
        aluno.setEmail(alunoDetails.getEmail());
        aluno.setTelefone(alunoDetails.getTelefone());

        return alunoRepository.save(aluno);
    }

    public void delete(Long id) {
        Aluno aluno = findById(id);
        alunoRepository.delete(aluno);
    }
}


