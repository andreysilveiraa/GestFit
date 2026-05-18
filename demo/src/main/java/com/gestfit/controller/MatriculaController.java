package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.model.Plano;
import com.gestfit.service.MatriculaService;
import com.gestfit.service.PlanoService;
import com.gestfit.repository.AlunoRepository; // Ou AlunoService se seu grupo criou um
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

    @GetMapping("/nova")
    public String carregarTelaNovaMatricula(Model model) {
        model.addAttribute("listaAlunos", alunoRepo.findAll());

        model.addAttribute("listaPlanos", planoService.listarPlanosAtivos());

        return "matriculas/nova-matricula";
    }

    @PostMapping("/salvar")
    public String realizarNovaMatricula(@RequestParam("alunoId") Long alunoId,
                                        @RequestParam("planoId") Long planoId) {

        Aluno aluno = alunoRepo.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Plano plano = planoService.buscarPorId(planoId)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));

        matriculaService.realizarMatricula(aluno, plano);

        return "redirect:/matriculas/nova?sucesso";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarMatricula(@PathVariable("id") Long id) {
        matriculaService.cancelarMatricula(id);
        return "redirect:/matriculas/nova";
    }
}