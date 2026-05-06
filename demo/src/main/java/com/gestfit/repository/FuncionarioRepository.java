package com.gestfit.repository;

import com.gestfit.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
    // já pode ser utilizar  métodos como save() e findAll()  para funcionarios
}
