package com.gestfit.controller;

import com.gestfit.service.AcessoService;
import com.gestfit.service.LogService;
import com.gestfit.model.LogAuditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private LogService logService; // Injetado para permitir a exibição da tela de auditoria

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

    // Método para processar a Recuperação de Senha (O que faltava do diagrama)
    @PostMapping("/recuperar-senha")
    public String recuperarSenha(@RequestParam String email, Model model) {
        try {
            acessoService.recuperarSenha(email);
            model.addAttribute("mensagem", "Se o e-mail existir, as instruções foram enviadas!");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar recuperação: " + e.getMessage());
        }
        return "recuperar-senha"; // Nome da sua página HTML de recuperação de senha
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

    // --- TELA DE LOGS DE AUDITORIA (O que faltava) ---
    // Rota GET para abrir a página de relatório de auditoria e listar os logs
    @GetMapping("/auditoria")
    public String visualizarAuditoria(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            Model model) {

        List<LogAuditoria> logs;

        // Se o administrador filtrar por data, busca no período, senão traz todos
        if (inicio != null && fim != null) {
            logs = logService.consultarLogsPorData(inicio, fim);
        } else {
            // Criaremos um método findByTimestampBetween padrão ou findAll se preferir simplificar
            // Para garantir que funcione sem mexer no repository agora, usamos o findAll() herdado:
            logs = logService.consultarLogsPorData(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        }

        model.addAttribute("logs", logs);
        return "auditoria"; // Nome da página HTML (ex: auditoria.html) que vai listar a tabela de logs
    }
}