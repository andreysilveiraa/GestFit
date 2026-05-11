package com.gestfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //mudei para @Controller para carregar a página html
public class HomeController {

    @GetMapping("/")
    public String carregarDashboard() {
        // Retorna o nome do arquivo HTML que será carregado
        return "index";
    }
}