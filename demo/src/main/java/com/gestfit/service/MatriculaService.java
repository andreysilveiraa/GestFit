package com.gestfit.service;

import com.gestfit.model.Matricula;
import com.gestfit.model.StatusMatricula;
import com.gestfit.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestfit.model.Aluno;
import com.gestfit.model.Plano;

import java.time.LocalDate;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepo;

    public Matricula realizarMatricula(Aluno aluno, Plano plano) {
        if (!plano.getAtivo()) {
            throw new IllegalArgumentException("Não é possível realizar matrícula em um plano inativo.");
        }

        Matricula novaMatricula = new Matricula();
        novaMatricula.setAluno(aluno);
        novaMatricula.setPlano(plano);

        novaMatricula.setDataInicio(LocalDate.now());

        int mesesDuracao = plano.getDuracaoMeses();
        novaMatricula.setDataTermino(LocalDate.now().plusMonths(mesesDuracao));

        novaMatricula.setStatus(StatusMatricula.ATIVA);

        return matriculaRepo.save(novaMatricula);
    }

    public void verificarInadimplencia(Long matriculaId) {
        matriculaRepo.findById(matriculaId).ifPresent(m -> {
            if (LocalDate.now().isAfter(m.getDataTermino())) {
                m.alterarStatus(StatusMatricula.PENDENTE);
                matriculaRepo.save(m);
            }
        });
    }

    public boolean alunoPodeAcessar(Long alunoId) {
        return matriculaRepo.findByAlunoId(alunoId)
                .map(m -> m.getStatus() == StatusMatricula.ATIVA && LocalDate.now().isBefore(m.getDataTermino()))
                .orElse(false);
    }

    public void cancelarMatricula(Long matriculaId) {
        matriculaRepo.findById(matriculaId).ifPresent(m -> {
            m.alterarStatus(StatusMatricula.CANCELADA);
            matriculaRepo.save(m);
            System.out.println("Matrícula " + matriculaId + " foi cancelada com sucesso.");
        });
    }
}
