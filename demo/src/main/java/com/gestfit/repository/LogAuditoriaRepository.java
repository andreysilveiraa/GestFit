package com.gestfit.repository;

import com.gestfit.model.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {

    // Método extra para buscar logs por um período de tempo, como pede o diagrama
    List<LogAuditoria> findByTimestampBetween(LocalDateTime inicio, LocalDateTime fim);
}