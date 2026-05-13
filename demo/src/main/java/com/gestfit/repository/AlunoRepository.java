package com.gestfit.repository;

import com.gestfit.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long> {
        // já pode ser utilizar  métodos como save() e findAll()  para alunos

    // O Spring Boot criará a lógica de busca automaticamente apenas por esta linha:
    Optional<Aluno> findByCpf(String cpf);
    Optional<Aluno> findByMatricula(String matricula);
    List<Aluno> findByMatriculaContaining(String parteMatricula);
}
