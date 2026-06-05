package com.gestfit.repository;

import com.gestfit.model.Academia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AcademiaRepository extends JpaRepository<Academia, Long> {
    // Auxiliar útil para validar se o CNPJ já existe antes de cadastrar
    Optional<Academia> findByCnpj(String cnpj);

    // Auxiliar útil para o futuro fluxo de Login da Unidade (RF25)
    Optional<Academia> findByEmail(String email);
}