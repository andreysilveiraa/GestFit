package com.gestfit.repository;

import com.gestfit.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository <Despesa, Long> {

}
