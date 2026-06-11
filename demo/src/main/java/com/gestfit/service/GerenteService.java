package com.gestfit.service;

import com.gestfit.model.Gerente;
import com.gestfit.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepo;

    public List<Gerente> listarTodos() {
        return gerenteRepo.findAll();
    }

    public void cadastrarGerente(Gerente g) {
        if (gerenteRepo.findByCpf(g.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe este CPF cadastrado");
        }
        if (gerenteRepo.findByMatricula(g.getMatricula()).isPresent()) {
            throw new RuntimeException("Já existe esta matrícula cadastrada");
        }
        gerenteRepo.save(g);
    }

    public void editarGerente(Gerente g) {
        if (!gerenteRepo.findByMatricula(g.getMatricula()).isPresent()) {
            throw new RuntimeException("Gerente não encontrado no sistema");
        }
        gerenteRepo.save(g);
    }

    public void excluirGerente(String matricula) {
        if (!gerenteRepo.findByMatricula(matricula).isPresent()) {
            throw new RuntimeException("Gerente não encontrado no sistema");
        }
        gerenteRepo.deleteByMatricula(matricula);
    }
}