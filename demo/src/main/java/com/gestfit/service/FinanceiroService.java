package com.gestfit.service;

import com.gestfit.dto.RegistrarPagamentoDTO;
import com.gestfit.model.*;
import com.gestfit.repository.MatriculaRepository;
import com.gestfit.repository.ProfessorRepository;
import com.gestfit.repository.RecepcionistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FinanceiroService {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Método 1
    @Transactional
    public void registrarPagamento (RegistrarPagamentoDTO dto){
        Pagamento pagamento = pagamentoService.buscarPorId(dto.idPagamento());

        pagamento.registrarPagamento(dto.formaPagamento());

        pagamentoService.salvar(pagamento);
    }

    //Metodo 2
    public void gerarCobrancaAutomatica(){

        List<Matricula> matriculasAtivas = matriculaRepository.findAll().stream()
                .filter(m -> m.getStatus() == StatusMatricula.ATIVA)
                .collect(Collectors.toList());
        for (Matricula m : matriculasAtivas){
            Pagamento novoPagamento = new Pagamento();
            novoPagamento.setDataVencimento(java.time.LocalDate.now().plusMonths(1));
            novoPagamento.setStatusDoPagamento(StatusDoPagamento.PENDENTE);

            pagamentoService.salvar(novoPagamento);
        }
        System.out.println(">>> Cobranças automáticas geradas para " + matriculasAtivas.size() + " alunos ativos.");
    }

    //Metodo 3
    public void registrarDespesa (Object d){
        // Método Incompleto
        System.out.println(">>> Despesa resgistrada: " + d.toString());
    }

    //Metodo 4
    @Transactional(readOnly = true)
    public void processarSalarios (){
        List<Professor> professores = professorRepository.findAll();
        List<Recepcionista> recepcionistas = recepcionistaRepository.findAll();

        double totalFolha = 0.0;
        System.out.println(" === FOLHA DE PAGAMENTO === ");
        for (Professor p : professores){
            System.out.println("Funcionário: " + p.getNome() + " | Cargo: " + p.getCargo() + " | Salário: R$" + p.getSalarioBase());
            totalFolha += p.getSalarioBase();
        }

        for (Recepcionista r : recepcionistas){
            System.out.println("Funcionário: " + r.getNome() + " | Cargo: " + r.getCargo() + " | Salário: R$" + r.getSalarioBase());
            totalFolha += r.getSalarioBase();
        }

        System.out.println("=================================");
        System.out.println("Total da Folha de Pagamento: R$" + totalFolha);
    }

    //Metodo 5
    public Object gerarRelatorioFinanceiro(LocalDateTime inicio, LocalDateTime fim){
        // Método Incompleto
        System.out.println(">>> Gerando relatório financeiro de " + inicio + " até " + fim);
        return null;
    }

    //Método 6
    public List<Aluno> controlarInadimplentes(){

        return matriculaRepository.findAll().stream()
                .filter(m -> m.getStatus() == StatusMatricula.VENCIDA)
                .map(Matricula::getAluno)
                .distinct()
                .collect(Collectors.toList());
    }

}
