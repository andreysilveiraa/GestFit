package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired //ele vai instaciar o objeto por mim
    private AlunoRepository repo;

    @GetMapping
    public String getTodosAlunos (Model model){
        model.addAttribute("listaAlunos", repo.findAll());
        return "alunos"; //nome do arquivo html que vai ser carregado
    }

}
//localhost:8080/alunos