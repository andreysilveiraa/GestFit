package com.gestfit.service;

import com.gestfit.model.FolhaDePagamento;
import com.gestfit.repository.FolhaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private FolhaDePagamentoRepository folhaDePagamentoRepository;

    public FolhaDePagamento salvar(FolhaDePagamento folha) {
        return folhaDePagamentoRepository.save(folha);
    }
}
