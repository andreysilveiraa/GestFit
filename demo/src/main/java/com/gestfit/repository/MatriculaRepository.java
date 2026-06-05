package com.gestfit.repository;

import com.gestfit.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    // Busca a matrícula de um aluno específico para podermos validar o acesso dele
    Optional<Matricula> findByAlunoId(Long alunoId);
}