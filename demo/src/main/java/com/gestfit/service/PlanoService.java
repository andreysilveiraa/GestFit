package com.gestfit.service;

import com.gestfit.model.Plano;
import com.gestfit.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepo;

    public Plano salvarPlano(Plano plano) {
        if (plano.getId() == null) {
            plano.setAtivo(true);
        }
        return planoRepo.save(plano);
    }

    public List<Plano> listarTodosOsPlanos() {
        return planoRepo.findAll();
    }

    public List<Plano> listarPlanosAtivos() {
        return planoRepo.findByAtivoTrue();
    }

    public Optional<Plano> buscarPorId(Long id) {
        return planoRepo.findById(id);
    }

    public void inativarPlano(Long id) {
        planoRepo.findById(id).ifPresent(plano -> {
            plano.setAtivo(false);
            planoRepo.save(plano);
            System.out.println(">>> O plano '" + plano.getDescricao() + "' foi inativado.");
        });
    }
}