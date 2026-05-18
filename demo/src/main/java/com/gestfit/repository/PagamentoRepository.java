package com.gestfit.repository;

import com.gestfit.model.Pagamento;
import com.gestfit.model.StatusDoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository <Pagamento, Long> {

    List<Pagamento> findByStatusDoPagamento(StatusDoPagamento status);

}
