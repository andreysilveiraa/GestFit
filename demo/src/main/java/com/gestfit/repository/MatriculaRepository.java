package com.gestfit.repository;

import com.gestfit.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByAlunoId(Long alunoId);
}
