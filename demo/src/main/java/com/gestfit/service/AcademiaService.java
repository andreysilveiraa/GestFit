package com.gestfit.service;

import com.gestfit.model.Academia;
import com.gestfit.repository.AcademiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcademiaService {

    @Autowired
    private AcademiaRepository academiaRepo;

    // RF24 - Cadastrar Academia (Unidade)
    public Academia cadastrarAcademia(Academia academia) {
        // Validação de segurança simples: não permitir duplicar o CNPJ
        if (academiaRepo.findByCnpj(academia.getCnpj()).isPresent()) {
            throw new IllegalArgumentException("Uma academia com este CNPJ já está cadastrada.");
        }
        return academiaRepo.save(academia);
    }

    public List<Academia> listarTodas() {
        return academiaRepo.findAll();
    }

    public Optional<Academia> buscarPorId(Long id) {
        return academiaRepo.findById(id);
    }
}