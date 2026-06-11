package com.gestfit.controller;

import com.gestfit.model.Professor;
import com.gestfit.model.Recepcionista;
import com.gestfit.model.Gerente;
import com.gestfit.repository.ProfessorRepository;
import com.gestfit.repository.RecepcionistaRepository;
import com.gestfit.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private ProfessorRepository profRepo;

    @Autowired
    private RecepcionistaRepository recepRepo;

    @Autowired
    private GerenteRepository gerenteRepo;

    @GetMapping
    public String listarTodosOsFuncionarios(Model model) {

        List<Professor> professores = profRepo.findAll();
        List<Recepcionista> recepcionistas = recepRepo.findAll();
        List<Gerente> gerentes = gerenteRepo.findAll();

        List<com.gestfit.model.Funcionario> todosFuncionarios = new java.util.ArrayList<>();
        todosFuncionarios.addAll(professores);
        todosFuncionarios.addAll(recepcionistas);
        todosFuncionarios.addAll(gerentes);

        model.addAttribute("listaFuncionarios", todosFuncionarios);

        // Retorna o nome do arquivo HTML: src/main/resources/templates/funcionarios.html
        return "funcionarios";
    }
}
