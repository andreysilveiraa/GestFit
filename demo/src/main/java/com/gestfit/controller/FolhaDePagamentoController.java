package com.gestfit.controller;

import com.gestfit.model.FolhaDePagamento;
import com.gestfit.service.FolhaDePagamentoService;
import com.gestfit.repository.FolhaDePagamentoRepository; // Injetado para listagem direta e rápida
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/folha")
public class FolhaDePagamentoController {

    @Autowired
    private FolhaDePagamentoService folhaService;

    @Autowired
    private FolhaDePagamentoRepository folhaRepository;

    // Carrega a tela com o histórico de folhas processadas
    @GetMapping
    public String listarFolhas(Model model) {
        model.addAttribute("listaFolhas", folhaRepository.findAll());
        model.addAttribute("novaFolha", new FolhaDePagamento());
        return "funcionarios/folha-pagamento"; // Nome da sua página HTML correspondente
    }

    // Processa o salvamento/geração de uma nova folha de pagamento
    @PostMapping("/processar")
    public String processarFolha(@ModelAttribute("novaFolha") FolhaDePagamento folha) {
        // Define automaticamente a data de processamento como o dia de hoje
        folha.setDataProcessamento(LocalDate.now());

        // Usa o seu método salvar() do Service
        folhaService.salvar(folha);

        return "redirect:/folha?sucesso";
    }
}