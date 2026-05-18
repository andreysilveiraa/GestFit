package com.gestfit.repository;

import com.gestfit.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
//    // já pode ser utilizar  métodos como save() e findAll()  para funcionarios
//
//    Optional<Funcionario>findByCpf(String cpf);
//    Optional<Funcionario> findByMatricula(String matricula);
//    List<Funcionario> findByMatriculaContaining(String parteMatricula);
//} // Esse repositório não vai existir mais, pois Funcionario é uma classe abstrata.
//// Ele é apenas para exemplificar a estrutura de um repositório. O ideal seria criar um repositório para cada tipo de funcionário
//// , como ProfessorRepository e RecepcionistaRepository.
