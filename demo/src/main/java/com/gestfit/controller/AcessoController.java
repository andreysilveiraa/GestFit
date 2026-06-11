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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private LogService logService;

    // 1. Abre a tela de Dashboard Principal (Unificada)
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Abre o dashboard.html
    }

    // 2. Processa o formulário de "Login" enviado pelo index.html
    @PostMapping("/acesso/entrar")
    public String login(@RequestParam String login, @RequestParam String senha, Model model) {

        // 1. REGRA DE TESTE (PORTA DOS FUNDOS):
        // Se você digitar "admin" no usuario e "123" na senha, o sistema entra DIREITO!
        if ("admin".equalsIgnoreCase(login) && "123".equals(senha)) {
            return "redirect:/dashboard";
        }

        // 2. REGRA REAL (Se não for o admin de teste, ele tenta o banco de dados)
        try {
            boolean autenticado = acessoService.autenticar(login, senha);
            if (autenticado) {
                return "redirect:/dashboard";
            } else {
                model.addAttribute("erro", "Usuário ou senha incorretos no banco de dados.");
                return "index";
            }
        } catch (Exception e) {
            // Se der qualquer erro de banco, avisa na tela ao invés de travar o sistema
            model.addAttribute("erro", "Erro ao conectar com o banco: " + e.getMessage());
            return "index";
        }
    }

    // 3. Processa a Recuperação de Senha
    @PostMapping("/acesso/recuperar-senha")
    public String recuperarSenha(@RequestParam String email, Model model) {
        try {
            acessoService.recuperarSenha(email);
            model.addAttribute("mensagem", "Se o e-mail existir, as instruções foram enviadas!");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar recuperação: " + e.getMessage());
        }
        return "recuperar-senha";
    }

    // 4. Registra a frequência do aluno por "ID"
    @PostMapping("/acesso/registrar-presenca")
    public String registrarPresenca(@RequestParam Long alunoId, Model model) {
        try {
            acessoService.registrarEntrada(alunoId);
            model.addAttribute("mensagem", "Presença registrada com sucesso!");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao registrar: " + e.getMessage());
        }
        return "index";
    }

    // 5. Visualização dos "Logs" de Auditoria
    @GetMapping("/acesso/auditoria")
    public String visualizarAuditoria(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            Model model) {

        List<LogAuditoria> logs;
        if (inicio != null && fim != null) {
            logs = logService.consultarLogsPorData(inicio, fim);
        } else {
            logs = logService.consultarLogsPorData(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        }
        model.addAttribute("logs", logs);
        return "auditoria";
    }
}