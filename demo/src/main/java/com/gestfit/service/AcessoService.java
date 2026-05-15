package com.gestfit.service;

import com.gestfit.model.Frequencia;
import com.gestfit.model.Usuario;
import com.gestfit.repository.FrequenciaRepository;
import com.gestfit.repository.AlunoRepository;
import com.gestfit.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AcessoService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- MÉTODOS DE AUTENTICAÇÃO (O QUE FALTAVA) ---

    public boolean autenticar(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        // Verifica se usuário existe e se a senha confere
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public void recuperarSenha(String email) {
        // Lógica para enviar e-mail de recuperação
        System.out.println("Enviando recuperação de senha para: " + email);
    }

    public boolean reautenticar(String senha) {
        // Lógica para confirmar senha do usuário já logado
        return true;
    }

    // --- MÉTODOS DE ACESSO DO ALUNO ---

    public boolean validarAcessoAluno(Long idAluno) {
        return alunoRepository.findById(idAluno)
                .map(aluno -> aluno.isAtivo())
                .orElse(false);
    }

    public void registrarEntrada(Long idAluno) {
        if (validarAcessoAluno(idAluno)) {
            alunoRepository.findById(idAluno).ifPresent(aluno -> {
                Frequencia f = new Frequencia();
                f.setDataHoraEntrada(LocalDateTime.now());
                f.setAluno(aluno);
                frequenciaRepository.save(f);
            });
        } else {
            throw new RuntimeException("Acesso negado: Aluno inativo ou não encontrado.");
        }
    }
}