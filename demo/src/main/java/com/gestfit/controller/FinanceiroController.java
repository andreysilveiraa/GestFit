package com.gestfit.controller;

import com.gestfit.dto.RegistrarPagmentoDTO;
import com.gestfit.model.Aluno;
import com.gestfit.service.FinanceiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {

    @Autowired
    private FinanceiroService financeiroService;

    @GetMapping("/painel")
    public String exibirPainel(Model model){
        model.addAttribute("registrarPagmentoDTO", new RegistrarPagmentoDTO(null, 0.0, null));
        return "financeiro/painel-financeiro";
    }
    @PostMapping("/baixar")
    public String processarBaixaPagamento(@Valid @ModelAttribute("registrarPagamentoDTO") RegistrarPagmentoDTO dto, BindingResult result,
                                          RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "financeiro/painel-financeiro";
        }
        try {
            financeiroService.registrarPagamento(dto);
            redirectAttributes.addFlashAttribute("MensagemSucesso", "Pagamento baixado com sucesso!");

        }
        catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        }

        return "redirect:/financeiro/painel";
    }

    @PostMapping("/salarios/processar")
    public String fecharFolhaSalarios(RedirectAttributes redirectAttributes) {
        try {
            financeiroService.processarSalarios();
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Folha de pagamento calculada com sucesso no console!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao processar salários: " + e.getMessage());
        }
        return "redirect:/financeiro/painel";
    }

    @GetMapping("/inadimplentes")
    public String listarInadimplentes(Model model){
        List<Aluno> alunosInadimplentes = financeiroService.controlarInadimplentes();
        model.addAttribute("listaInadimplentes", alunosInadimplentes);
        return "financeiro/lista-inadimplentes";
    }
}
