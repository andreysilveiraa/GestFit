package com.gestfit.repository;

import com.gestfit.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {
    // Busca apenas os planos que estão ativos para venda na academia
    List<Plano> findByAtivoTrue();
}