package com.gestfit.repository;

import com.gestfit.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findByCpf(String cpf);
    Optional<Gerente> findByMatricula(String matricula);
    void deleteByMatricula(String matricula);
}