package com.gestfit.config;

import com.gestfit.model.Aluno;
import com.gestfit.model.Funcionario;
import com.gestfit.repository.AlunoRepository;
import com.gestfit.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AlunoRepository alunoRepo, FuncionarioRepository funcRepo){
        return args -> {
            List<Aluno> novosAlunos = List.of(
                    new Aluno("Marcos Silva", "987.654.321-11", "20000"),
                    new Aluno("Clara Souza", "555.444.333-22", "20001")
            );

            for (Aluno a : novosAlunos){
                if (alunoRepo.findByCpf (a.getCpf()).isEmpty()){
                    alunoRepo.save(a);
                }
            }

            List<Funcionario> novosFuncionarios = List.of(
                    new Funcionario("Ana Pereira", "111.222.333-44", "F-0001"),
                    new Funcionario("Carlos Oliveira", "666.777.888-55", "F-0002")
            );

            for (Funcionario f : novosFuncionarios){
                if (funcRepo.findByCpf (f.getCpf()).isEmpty()){
                    funcRepo.save(f);
                }
            }
        };
    }

}
