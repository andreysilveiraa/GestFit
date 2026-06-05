package com.gestfit.service;

import com.gestfit.model.Aluno;
import com.gestfit.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepo;

    public Aluno salvarAluno(Aluno aluno) {

        if (aluno.getId() == null) {
            aluno.setAtivo(true);
        }
        return alunoRepo.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepo.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepo.findById(id);
    }

    public void inativarAluno(Long id) {
        alunoRepo.findById(id).ifPresent(aluno -> {
            aluno.setAtivo(false);
            alunoRepo.save(aluno);
            System.out.println(">>> Aluno '" + aluno.getNome() + "' foi inativado com sucesso.");
        });
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoRepo.findByMatricula(matricula);
    }
}