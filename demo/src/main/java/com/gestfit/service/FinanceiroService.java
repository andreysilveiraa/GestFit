package com.gestfit.service;

import com.gestfit.dto.DespesaDTO;
import com.gestfit.dto.RegistrarPagamentoDTO;
import com.gestfit.model.*;
import com.gestfit.repository.MatriculaRepository;
import com.gestfit.repository.ProfessorRepository;
import com.gestfit.repository.RecepcionistaRepository;
import com.gestfit.repository.GerenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
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

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private FolhaDePagamentoService folhaDePagamentoService;

    // Adicione a injeção do novo repositório no topo da classe FinanceiroService
    @Autowired
    private GerenteRepository gerenteRepository;

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
    public void registrarDespesa (DespesaDTO dto){
        Despesa despesa = new Despesa(
                dto.descricao(),
                dto.valor(),
                dto.dataVencimento(),
                dto.categoria()
        );
        despesaService.salvar(despesa);
        System.out.println(">>> Despesa registrada no Supabase: " + despesa.getDescricao());
    }

    //Metodo 4
    @Transactional
    public void processarSalarios() {
        // Busca todas as 3 categorias de funcionários que geram custos salariais
        List<Professor> professores = professorRepository.findAll();
        List<Recepcionista> recepcionistas = recepcionistaRepository.findAll();
        List<Gerente> gerentes = gerenteRepository.findAll(); // Incluído Gerente

        double totalFolha = 0.0;

        for (Professor p : professores){
            totalFolha += p.getSalarioBase();
        }

        for (Recepcionista r : recepcionistas){
            totalFolha += r.getSalarioBase();
        }

        // Loop para somar o salário dos gerentes na folha global da academia
        for (Gerente g : gerentes) {
            totalFolha += g.getSalarioBase();
        }

        int mesAtual = LocalDateTime.now().getMonthValue();

        FolhaDePagamento novaFolha = new FolhaDePagamento(mesAtual, totalFolha, LocalDate.now());
        folhaDePagamentoService.salvar(novaFolha);

        Despesa despesaSalarial = new Despesa(
                "Folha de Pagamento - Mês " + mesAtual,
                totalFolha,
                LocalDate.now(),
                CategoriaDaDespesa.SALARIOS
        );

        despesaService.salvar(despesaSalarial);
        System.out.println(">>> Folha do mês " + mesAtual + " processada com Professores, Recepcionistas e Gerentes: R$" + totalFolha);
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

    public List<Despesa> listarTodasAsDespesas() {
        return despesaService.listarTodas();
    }

}
