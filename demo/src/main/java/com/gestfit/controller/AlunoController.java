package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.service.AlunoService; // Corrigido: Importando o Service agora
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Corrigido: Usando Controller para Thymeleaf/HTML
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

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

        model.addAttribute("novoAluno", new com.gestfit.model.Aluno());
        model.addAttribute("statusOpcoes", com.gestfit.model.StatusMatricula.values());

        model.addAttribute("planoOpcoes", com.gestfit.model.Tipos.values());

        return "alunos"; // Nome do arquivo alunos.html
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute("novoAluno") com.gestfit.model.Aluno aluno,
                              @RequestHeader(value = "Referer", required = false) String referer) {

        // 1. Salva o novo aluno no banco de dados através do seu Service
        alunoService.salvarAluno(aluno); // Verifique se o seu service usa o nome .salvar() ou .cadastrar()

        // 2. O truque mágico: se ele souber de onde você veio (tela de alunos ou inadimplentes), ele te joga de volta para lá
        if (referer != null) {
            return "redirect:" + referer;
        }

        // 3. Caso falhe o referer, ele volta para a rota padrão de alunos
        return "redirect:/alunos";
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