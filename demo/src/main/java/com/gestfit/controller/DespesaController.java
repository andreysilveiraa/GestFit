package com.gestfit.controller;

import com.gestfit.dto.DespesaDTO;
import com.gestfit.service.FinanceiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // sempre escolher a primeira opção do import
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private FinanceiroService financeiroService;

    @GetMapping("/nova")
    public String exibirFormularioCadastro (Model model){
        model.addAttribute("despesaDTO", new DespesaDTO(null, null, null, null));
        return "financeiro/formulario-despesa";
    }

    public String salvarDespesa (@Valid @ModelAttribute("despesaDTO") DespesaDTO despesaDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes
                                 )
    {
        if (result.hasErrors()){
            return "financeiro/formulario-despesa";
        }
        try {
            financeiroService.registrarDespesa(despesaDTO);
            redirectAttributes.addFlashAttribute("MensagemSucesso", "Despesa registrada com sucesso!");
        }
        catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("MensagemErro", "Erro ao registrar despesa: " + e.getMessage());

        }

        return "redirect:/financeiro/painel";
    }


}
