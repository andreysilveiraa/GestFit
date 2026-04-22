package com.gestfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String carregarDashboard() {
        // Retorna o nome do arquivo HTML que será carregado
        return "index";
    }
}