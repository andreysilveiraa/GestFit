package com.gestfit.service;

import com.gestfit.model.Plano;
import com.gestfit.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public void cadastrarPlano(Plano plano) {
        plano.setAtivo(true);
        planoRepository.save(plano);
    }

    public void editarPlano(Plano plano) {
        planoRepository.save(plano);
    }

    public void excluirPlano(Long id) {
        planoRepository.findById(id).ifPresent(plano -> {
            plano.setAtivo(false); // Exclusão lógica para não quebrar o histórico de matrículas
            planoRepository.save(plano);
        });
    }

    public Plano consultarPlano(Long id) {
        return planoRepository.findById(id).orElse(null);
    }

    public List<Plano> listarTodos() {
        return planoRepository.findByAtivoTrue();
    }
}