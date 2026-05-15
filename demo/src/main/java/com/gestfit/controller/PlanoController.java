package com.gestfit.controller;

import com.gestfit.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private  PlanoRepository planoRepo;

    @GetMapping
    public String listarPlanos(Model model) {
        model.addAttribute("listaPlanos", planoRepo.findAll());
        return "planos";
    }
}
