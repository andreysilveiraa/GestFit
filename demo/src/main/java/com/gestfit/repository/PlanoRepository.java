package com.gestfit.repository;

import com.gestfit.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    List<Plano> findByAtivoTrue();
}
