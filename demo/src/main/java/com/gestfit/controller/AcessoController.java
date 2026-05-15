package com.gestfit.controller;

import com.gestfit.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    // Método para processar o Login
    @PostMapping("/entrar")
    public String login(@RequestParam String login, @RequestParam String senha, Model model) {
        boolean autenticado = acessoService.autenticar(login, senha);
        if (autenticado) {
            return "redirect:/"; // Redireciona para a home se der certo
        } else {
            model.addAttribute("erro", "Login ou senha inválidos");
            return "index"; // Volta para a tela de login com erro
        }
    }

    // Método para registrar a frequência do aluno (conforme seu diagrama)
    @PostMapping("/registrar-presenca")
    public String registrarPresenca(@RequestParam Long alunoId, Model model) {
        try {
            acessoService.registrarEntrada(alunoId);
            model.addAttribute("mensagem", "Presença registrada com sucesso!");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao registrar: " + e.getMessage());
        }
        return "index";
    }
}