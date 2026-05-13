package com.gestfit.controller;

import com.gestfit.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repo;

    @GetMapping
    public String listarFuncionarios(Model model) {
        // Busca todos os funcionários no Supabase
        // Adiciona à "sacola" (model) para o Thymeleaf ler
        model.addAttribute("listaFuncionarios", repo.findAll());

        // Retorna o nome do arquivo HTML: src/main/resources/templates/funcionarios.html
        return "funcionarios";
    }
}
