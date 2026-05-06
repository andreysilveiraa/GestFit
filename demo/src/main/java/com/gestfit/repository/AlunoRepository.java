package com.gestfit.repository;

import com.gestfit.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long> {
        // já pode ser utilizar  métodos como save() e findAll()  para alunos
}
