package com.gestfit.repository;

import com.gestfit.model.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista,Long> {
    Optional<Recepcionista>findByCpf(String cpf);
    Optional<Recepcionista>findByMatricula(String matricula);
    void deleteByMatricula(String matricula);
}
