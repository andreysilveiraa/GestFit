package com.gestfit.config;

import com.gestfit.model.Aluno;
import com.gestfit.repository.AlunoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AlunoRepository repo){
        return args -> {
            List<Aluno> novosAlunos = List.of(
                    new Aluno("Marcos Silva", "987.654.321-11"),
                    new Aluno("Clara Souza", "555.444.333-22")
            );

            for (Aluno a : novosAlunos){
                if (repo.findByCpf (a.getCpf()).isEmpty()){
                    repo.save(a);
                }
            }
        };
    }
}
