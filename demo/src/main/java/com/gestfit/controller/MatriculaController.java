package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.model.Plano;
import com.gestfit.service.MatriculaService;
import com.gestfit.service.PlanoService;
import com.gestfit.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private PlanoService planoService;

    @Autowired
    private AlunoRepository alunoRepo;

    // Exibe o formulário de nova matrícula injetando alunos e planos ativos
    @GetMapping("/nova")
    public String carregarTelaNovaMatricula(Model model) {
        model.addAttribute("listaAlunos", alunoRepo.findAll());
        model.addAttribute("listaPlanos", planoService.listarTodos());
        return "matriculas/nova-matricula";
    }

    // Recebe a requisição do formulário para salvar a nova matrícula
    @PostMapping("/salvar")
    public String realizarNovaMatricula(@RequestParam("alunoId") Long alunoId,
                                        @RequestParam("planoId") Long planoId) {

        Aluno aluno = alunoRepo.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Plano plano = planoService.consultarPlano(planoId);
        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado");
        }

        // Chama o método que criamos/garantimos no service acima
        matriculaService.realizarMatricula(aluno, plano);

        return "redirect:/matriculas/nova?sucesso";
    }

    // Rota para cancelar uma matrícula específica
    @PostMapping("/cancelar/{id}")
    public String cancelarMatricula(@PathVariable("id") Long id) {
        matriculaService.processarCancelamento(id);
        return "redirect:/matriculas/nova";
    }
}