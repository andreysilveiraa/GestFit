package com.gestfit.config;

import com.gestfit.model.*;
import com.gestfit.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AlunoRepository alunoRepo,
                                   FuncionarioRepository funcRepo,
                                   UsuarioRepository usuarioRepo,
                                   PlanoRepository planoRepo,
                                   MatriculaRepository matriculaRepo) {
        return args -> {
            // --- Alunos ---
            List<Aluno> novosAlunos = List.of(
                    new Aluno("Marcos Silva", "987.654.321-11", "20000"),
                    new Aluno("Clara Souza", "555.444.333-22", "20001")
            );

            for (Aluno a : novosAlunos) {
                if (alunoRepo.findByCpf(a.getCpf()).isEmpty()) {
                    alunoRepo.save(a);
                }
            }

            // --- Funcionários ---
            List<Funcionario> novosFuncionarios = List.of(
                    new Funcionario("Ana Pereira", "111.222.333-44", "F-0001"),
                    new Funcionario("Carlos Oliveira", "666.777.888-55", "F-0002")
            );

            for (Funcionario f : novosFuncionarios) {
                if (funcRepo.findByCpf(f.getCpf()).isEmpty()) {
                    funcRepo.save(f);
                }
            }

            // --- PARTE DO ANDREY: Usuários do Sistema ---
            if (usuarioRepo.count() == 0) {
                Usuario admin = new Usuario();
                admin.setNome("Andrey Admin");
                admin.setCpf("000.000.000-00");
                admin.setLogin("admin");
                admin.setSenha("admin123");
                admin.setPerfil(PerfilUsuario.ADMIN);
                usuarioRepo.save(admin);

                Usuario prof = new Usuario();
                prof.setNome("Professor Teste");
                prof.setCpf("111.111.111-11");
                prof.setLogin("prof");
                prof.setSenha("prof123");
                prof.setPerfil(PerfilUsuario.PROFESSOR);
                usuarioRepo.save(prof);

                System.out.println(">>> Usuários de teste (admin e prof) criados com sucesso!");
            }

            // PARTE DA JULIANA: Plano e Matrícula
            if (planoRepo.count() == 0) {
                Plano planoMensal = new Plano(Tipos.MENSAL, 120.0, 1, "Plano Mensal Básico");
                Plano planoAnual = new Plano(Tipos.ANUAL, 1000.0, 12, "Plano Anual Completo com desconto");

                planoRepo.save(planoMensal);
                planoRepo.save(planoAnual);
                System.out.println(">>> Planos de teste criados com sucesso!");

                List<Aluno> alunosCadastrados = alunoRepo.findAll();
                if (!alunosCadastrados.isEmpty()) {
                    Aluno primeiroAluno = alunosCadastrados.get(0); // Pega o Marcos Silva

                    Matricula matriculaTeste = new Matricula();
                    matriculaTeste.setAluno(primeiroAluno);
                    matriculaTeste.setPlano(planoAnual);
                    matriculaTeste.setDataInicio(LocalDate.now());
                    matriculaTeste.setDataTermino(LocalDate.now().plusMonths(planoAnual.getDuracaoMeses()));
                    matriculaTeste.setStatus(StatusMatricula.ATIVA);

                    matriculaTeste.setStatus(StatusMatricula.ATIVA);

                    matriculaRepo.save(matriculaTeste);
                    System.out.println(">>> Matrícula de teste vinculada ao aluno " + primeiroAluno.getNome() + " criada com sucesso!");
                }
            }
        };
    }
}