package com.gestfit.service;

import com.gestfit.model.Recepcionista;
import com.gestfit.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository recepRepo;

    public List<Recepcionista> listarTodosAsRecepcionistas() {
        return recepRepo.findAll();
    }

     public void cadastrarRecepcionista (Recepcionista r){
         if(recepRepo.findByCpf(r.getCpf()).isPresent()) {
             throw new RuntimeException("Já existe este CPF cadastrado");
         }
         if(recepRepo.findByMatricula(r.getMatricula()).isPresent()) {
             throw new RuntimeException("Já existe esta matrícula cadastrada");
         }
         recepRepo.save(r);
     }

     public void editarRecepcionista (Recepcionista r){
         if(!recepRepo.findByMatricula(r.getMatricula()).isPresent()){
             throw new RuntimeException("Recepcionista não encontrado no sistema");
         }
         recepRepo.save(r);
     }

     public void excluirRecepcionista (String matricula){
         if(!recepRepo.findByMatricula(matricula).isPresent()){
             throw new RuntimeException("Recepcionista não encontrado no sistema");
         }
         recepRepo.deleteByMatricula(matricula);
     }
}
