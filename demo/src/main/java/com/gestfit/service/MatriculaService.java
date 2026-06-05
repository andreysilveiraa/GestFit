package com.gestfit.service;

import com.gestfit.model.Aluno;
import com.gestfit.model.Matricula;
import com.gestfit.model.Plano;
import com.gestfit.model.StatusMatricula;
import com.gestfit.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Realiza a matrícula vinculando o Aluno ao Plano e calculando vigência
    public void realizarMatricula(Aluno aluno, Plano plano) {
        Matricula novaMatricula = new Matricula();
        novaMatricula.setAluno(aluno);
        novaMatricula.setPlano(plano);

        // Define a data de início como o dia de hoje
        novaMatricula.setDataInicio(LocalDate.now());

        // Calcula a data de término somando a duração em meses do plano selecionado
        novaMatricula.setDataTermino(LocalDate.now().plusMonths(plano.getDuracaoMeses()));

        // Define o status inicial como ATIVA
        novaMatricula.setStatus(StatusMatricula.ATIVA);

        // Salva a nova matrícula no banco de dados
        matriculaRepository.save(novaMatricula);
    }

    // Regra essencial usada pelo AcessoService para liberar a catraca
    public boolean alunoPodeAcessar(Long idAluno) {
        return matriculaRepository.findByAlunoId(idAluno)
                .map(matricula -> {
                    boolean ativa = matricula.getStatus() == StatusMatricula.ATIVA;
                    boolean dentroDoPrazo = !LocalDate.now().isAfter(matricula.getDataTermino());
                    return ativa && dentroDoPrazo;
                })
                .orElse(false);
    }

    // Cancela a matrícula alterando o status para CANCELADA
    public void processarCancelamento(Long idMatricula) {
        matriculaRepository.findById(idMatricula).ifPresent(matricula -> {
            matricula.alterarStatus(StatusMatricula.CANCELADA);
            matriculaRepository.save(matricula);
        });
    }

    // Verifica se a matrícula possui pendências financeiras
    public boolean verificarInadimplencia(Long idMatricula) {
        return matriculaRepository.findById(idMatricula)
                .map(matricula -> matricula.getStatus() == StatusMatricula.PENDENTE)
                .orElse(true);
    }
}