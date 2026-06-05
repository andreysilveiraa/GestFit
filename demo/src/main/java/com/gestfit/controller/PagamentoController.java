package com.gestfit.controller;

import com.gestfit.model.Pagamento;
import com.gestfit.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public String listarTodos(Model model){
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        model.addAttribute("ListaPagamentos", pagamentos);
        return "financeiro/lista-pagamentos"; // Vai renderizar o arquivo lista-pagamentos.html
    }

    @GetMapping("/detalhes/{id}") // Abre detalhes de uma única parcela específica
    public String exibirDetalhesPagamento(@PathVariable Long id, Model model) {
        try {
            Pagamento pagamento = pagamentoService.buscarPorId(id);
            model.addAttribute("pagamento", pagamento);
            return "financeiro/detalhes-pagamento"; // Vai renderizar o arquivo detalhes-pagamento.html
        } catch (RuntimeException e) {
            model.addAttribute("MensagemErro", e.getMessage());
            return "erro";
        }
    }

    // ADICIONADO: Processa o envio do formulário para registrar um novo pagamento de aluno
    @PostMapping("/salvar")
    public String salvarPagamento(@ModelAttribute("pagamento") Pagamento pagamento) {
        pagamentoService.salvar(pagamento);
        return "redirect:/pagamentos?sucesso";
    }
}