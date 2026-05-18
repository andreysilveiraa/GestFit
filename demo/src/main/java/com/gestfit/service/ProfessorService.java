package com.gestfit.service;

import com.gestfit.model.Professor;
import com.gestfit.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository profRepo;

    public List<Professor> listarTodosOsProfessores() {
        return profRepo.findAll();
    }

    public void cadastrarProfessor (Professor p){
        if(profRepo.findByCpf(p.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe este CPF cadastrado");
        }
        if(profRepo.findByMatricula(p.getMatricula()).isPresent()) {
            throw new RuntimeException("Já existe esta matrícula cadastrada");
    }
        profRepo.save(p);
    }

    public void editarProfessor (Professor p){
        if(!profRepo.findByMatricula(p.getMatricula()).isPresent()){
            throw new RuntimeException("Professor não encontrado no sistema");
        }
        profRepo.save(p);
    }

    public void excluirProfessor (String matricula){
        if(!profRepo.findByMatricula(matricula).isPresent()){
                throw new RuntimeException("Professor não encontrado no sistema");
            }
        profRepo.deleteByMatricula(matricula);
    }
}
