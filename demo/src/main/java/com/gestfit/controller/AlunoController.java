package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.service.AlunoService; // Corrigido: Importando o Service agora
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Corrigido: Usando Controller para Thymeleaf/HTML
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller// MUDADO AQUI (Permite renderizar os arquivos .html)
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService; // MUDADO AQUI! (Conversa com o Service, não com o Repo)

    @GetMapping
    public String getTodosAlunos(Model model) {
        // Usa o service para listar e manda a lista para o Thymeleaf
        model.addAttribute("listaAlunos", alunoService.listarTodos());
        return "alunos"; // Nome do arquivo alunos.html
    }

    @GetMapping("/buscar")
    public String buscarPorMatricula(@RequestParam String matricula, Model model) {
        // Como a busca por matrícula é uma busca específica do banco de dados,
        // criamos a ponte chamando o metodo adequado no Service
        Optional<Aluno> aluno = alunoService.buscarPorMatricula(matricula);

        if (aluno.isPresent()) {
            model.addAttribute("aluno", aluno.get());
            return "detalhes-aluno"; // detalhes-aluno.html
        }
        return "aluno-nao-encontrado"; // aluno-nao-encontrado.html
    }
}
//localhost:8080/alunos