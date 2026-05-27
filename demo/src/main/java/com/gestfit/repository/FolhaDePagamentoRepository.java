package com.gestfit.repository;

import com.gestfit.model.FolhaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolhaDePagamentoRepository extends JpaRepository<FolhaDePagamento, Long> {

}
