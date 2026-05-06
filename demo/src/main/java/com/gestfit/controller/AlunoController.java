package com.gestfit.controller;

import com.gestfit.model.Aluno;
import com.gestfit.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired //ele vai instaciar o objeto por mim
    private AlunoRepository repo;

    @GetMapping
    public List<Aluno> getTodosAlunos() {
        return repo.findAll();
    }

}
//localhost:8080/alunos