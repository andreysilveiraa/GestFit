package com.gestfit.config;

import com.gestfit.model.*;
import com.gestfit.repository.*;
import com.gestfit.service.FinanceiroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AlunoRepository alunoRepo,
                                   UsuarioRepository usuarioRepo,
                                   PlanoRepository planoRepo,
                                   MatriculaRepository matriculaRepo,
                                   ProfessorRepository professorRepo,
                                   RecepcionistaRepository recepcionistaRepo,
                                   PagamentoRepository pagamentoRepo,
                                   FolhaDePagamentoRepository folhaDePagamentoRepo,
                                   DespesaRepository despesaRepo,
                                   FinanceiroService financeiroService
    ){
        return args -> {
            // --- Alunos ---
            List<Aluno> novosAlunos = List.of(
                    new Aluno("Marcos Silva", "987.654.321-11", "20000"),
                    new Aluno("Clara Souza", "555.444.333-22", "20001"),
                    new Aluno("Lucas Oliveira", "123.456.789-00", "20002")
            );

            for (Aluno a : novosAlunos) {
                if (alunoRepo.findByCpf(a.getCpf()).isEmpty()) {
                    alunoRepo.save(a);
                }
            }

            // --- Professores --- (Manu)
            List<Professor> novosProfessores = List.of(
                    new Professor(
                            "Ana Pereira", "111.222.333-44", "P-0001", "85988881111", "ana.prof@gestfit.com",
                            LocalDate.of(1993, 4, 15), "Professor I", 3500.0, LocalDate.now(),
                            "40h", "Manhã", "Musculação"
                    ),
                    new Professor(
                            "Bruno Costa", "222.333.444-55", "P-0002", "85988882222", "bruno.prof@gestfit.com",
                            LocalDate.of(1990, 11, 2), "Professor II", 3800.0, LocalDate.now(),
                            "30h", "Noite", "Crossfit"
                    )
            );

            for (Professor p : novosProfessores) {
                if (professorRepo.findByCpf(p.getCpf()).isEmpty()) {
                    professorRepo.save(p);
                    System.out.println(">>> Professor " + p.getNome() + " criado com sucesso!");
                }
            }

            // --- Recepcionistas --- (Manu)
            List<Recepcionista> novasRecepcionistas = List.of(
                    new Recepcionista(
                            "Carlos Oliveira", "666.777.888-55", "R-0001", "85999993333", "carlos.recep@gestfit.com",
                            LocalDate.of(1997, 8, 20), "Recepcionista Diurno", 2200.0, LocalDate.now(),
                            "44h", "Tarde"
                    ),
                    new Recepcionista(
                            "Mariana Lima", "777.888.999-66", "R-0002", "85999994444", "mariana.recep@gestfit.com",
                            LocalDate.of(1999, 1, 10), "Recepcionista Noturno", 2400.0, LocalDate.now(),
                            "44h", "Noite"
                    )
            );

            for (Recepcionista r : novasRecepcionistas) {
                if (recepcionistaRepo.findByCpf(r.getCpf()).isEmpty()) {
                    recepcionistaRepo.save(r);
                    System.out.println(">>> Recepcionista " + r.getNome() + " criada com sucesso!");
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
                    Aluno primeiroAluno = alunosCadastrados.get(0);

                    Matricula matriculaTeste = new Matricula();
                    matriculaTeste.setAluno(primeiroAluno);
                    matriculaTeste.setPlano(planoAnual);
                    matriculaTeste.setDataInicio(LocalDate.now());
                    matriculaTeste.setDataTermino(LocalDate.now().plusMonths(planoAnual.getDuracaoMeses()));
                    matriculaTeste.setStatus(StatusMatricula.ATIVA);

                    matriculaRepo.save(matriculaTeste); // Linha duplicada removida aqui
                    System.out.println(">>> Matrícula de teste vinculada ao aluno " + primeiroAluno.getNome() + " criada com sucesso!");
                }
            } //

            // --- PARTE DE PAGAMENTOS (Manu)
            if (pagamentoRepo.count() == 0) {
                // Teste 1: Um pagamento que nasce pendente
                Pagamento pag1t = new Pagamento();
                pag1t.setDataVencimento(LocalDate.now().plusDays(5));
                pagamentoRepo.save(pag1t);
                System.out.println(">>> Registro de Pagamento 1 (PENDENTE) criado no Supabase!");

                // Teste 2: Pagamento que nasce pendente, mas o aluno pagou via PIX
                Pagamento pag2t = new Pagamento();
                pag2t.setDataVencimento(LocalDate.now().minusDays(2));
                pag2t.registrarPagamento(FormaPagamento.PIX);
                pagamentoRepo.save(pag2t);
                System.out.println(">>> Registro de Pagamento 2 (PAGO via PIX) criado no Supabase!");
            }

            if (despesaRepo.count() == 0 && folhaDePagamentoRepo.count() == 0) {
                System.out.println("\n=== INICIANDO CONFIGURAÇÃO DE TESTE DO SUBMÓDULO FINANCEIRO ===");


                Despesa contaAluguel = new Despesa(
                        "Aluguel do Galpão da Academia",
                        4500.00,
                        LocalDate.now().plusDays(10),
                        CategoriaDaDespesa.ALUGUEL
                );
                despesaRepo.save(contaAluguel);
                System.out.println(">>> Teste A: Despesa manual de ALUGUEL criada no Supabase!");


                System.out.println(">>> Teste B: Chamando o orquestrador 'processarSalarios()'...");
                financeiroService.processarSalarios();

                System.out.println("=== FINALIZADO COM SUCESSO OS TESTES DO INTEGRADO FINANCEIRO ===\n");
            }
        };
    }
}