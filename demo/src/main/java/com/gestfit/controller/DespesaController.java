package com.gestfit.controller;

import com.gestfit.model.Despesa;
import com.gestfit.model.CategoriaDaDespesa;
import com.gestfit.service.DespesaService;
import com.gestfit.repository.DespesaRepository; // Importado para usar o delete direto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private DespesaRepository despesaRepository; // Injetado para dar suporte à exclusão direta

    // Carrega a página principal de despesas
    @GetMapping
    public String listarDespesas(Model model) {
        // CORRIGIDO: Usa o seu listarTodas()
        model.addAttribute("listaDespesas", despesaService.listarTodas());

        // Calcula o total direto na stream para não precisar criar outro método no seu service
        double total = despesaService.listarTodas().stream()
                .mapToDouble(Despesa::getValor)
                .sum();
        model.addAttribute("totalDespesas", total);

        model.addAttribute("categorias", CategoriaDaDespesa.values()); // Envia os valores da sua enum com descrição
        model.addAttribute("novaDespesa", new Despesa());
        return "despesas/gerenciar-despesas";
    }

    // Processa o salvamento de uma nova despesa
    @PostMapping("/salvar")
    public String salvarDespesa(@ModelAttribute("novaDespesa") Despesa despesa) {
        // CORRIGIDO: Usa o seu método salvar()
        despesaService.salvar(despesa);
        return "redirect:/despesas?sucesso";
    }

    // Processa a exclusão de uma despesa
    @PostMapping("/excluir/{id}")
    public String excluirDespesa(@PathVariable("id") Long id) {
        // CORRIGIDO: Deleta direto pelo repositório para manter seu Service enxuto
        despesaRepository.deleteById(id);
        return "redirect:/despesas?excluido";
    }
}