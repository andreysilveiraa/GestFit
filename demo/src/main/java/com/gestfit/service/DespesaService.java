package com.gestfit.service;

import com.gestfit.model.Despesa;
import com.gestfit.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa salvar(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

}
