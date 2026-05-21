package com.gestfit.service;

import com.gestfit.model.Pagamento;
import com.gestfit.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepo;

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepo.findById(id).orElseThrow(() -> new RuntimeException("Erro: registro de id não encontrado"));
    }

    public void salvar(Pagamento pagamento) {
        pagamentoRepo.save(pagamento);
    }

    public List<Pagamento> listarTodos(){
        return pagamentoRepo.findAll();
    }
}
