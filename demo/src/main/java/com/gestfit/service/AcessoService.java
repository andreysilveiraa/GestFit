package com.gestfit.service;

import com.gestfit.model.Frequencia;
import com.gestfit.model.Usuario;
import com.gestfit.model.TipoOperacao;
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

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private LogService logService; // Integrando o serviço de logs criado no passo anterior

    // --- MÉTODOS DE AUTENTICAÇÃO ---

    public boolean autenticar(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        boolean autenticado = usuario != null && usuario.getSenha().equals(senha);

        if (autenticado) {
            logService.registrarAcao(login, TipoOperacao.LOGIN, "Usuário autenticado com sucesso no sistema.");
        } else {
            logService.registrarAcao(login != null ? login : "DESCONHECIDO", TipoOperacao.LOGIN, "Falha na tentativa de login.");
        }

        return autenticado;
    }

    public void recuperarSenha(String email) {
        // Lógica para enviar e-mail de recuperação
        logService.registrarAcao("SISTEMA", TipoOperacao.REDEFINICAO_SENHA, "Solicitação de recuperação de senha para o e-mail: " + email);
        System.out.println("Enviando recuperação de senha para: " + email);
    }

    public boolean reautenticar(String senha) {
        // Lógica para confirmar senha do usuário já logado
        logService.registrarAcao("USUARIO_LOGADO", TipoOperacao.LOGIN, "Reautenticação de segurança realizada com sucesso.");
        return true;
    }

    // --- MÉTODOS DE ACESSO DO ALUNO (Com validação de matrícula ativa e Log) ---

    public boolean validarAcessoAluno(Long idAluno) {
        return alunoRepository.findById(idAluno)
                .map(aluno -> {
                    // 1. Verifica se o cadastro do aluno está ativo
                    boolean cadastroAtivo = aluno.isAtivo();
                    // 2. Chama a regra da Juliana para ver se a matrícula está ATIVA e em dia
                    boolean matriculaEmDia = matriculaService.alunoPodeAcessar(idAluno);
                    // Só libera se o cadastro estiver ativo E a matrícula em dia (RF15)
                    return cadastroAtivo && matriculaEmDia;
                })
                .orElse(false);
    }

    public void registrarEntrada(Long idAluno) {
        if (validarAcessoAluno(idAluno)) {
            alunoRepository.findById(idAluno).ifPresent(aluno -> {
                Frequencia f = new Frequencia();
                f.setDataHoraEntrada(LocalDateTime.now());
                f.setAluno(aluno);
                frequenciaRepository.save(f);

                // Grava o sucesso da entrada no log de auditoria
                logService.registrarAcao("CATRACA", TipoOperacao.LOGIN, "Acesso LIBERADO para o aluno: " + aluno.getNome() + " (ID: " + idAluno + ")");
            });
        } else {
            // Grava a tentativa frustrada no log de auditoria antes de lançar a exceção
            logService.registrarAcao("CATRACA", TipoOperacao.LOGIN, "Acesso NEGADO para o Aluno ID: " + idAluno + " (Inativo, inadimplente ou sem matrícula válida)");
            throw new RuntimeException("Acesso negado: Aluno inativo, inadimplente ou sem matrícula válida.");
        }
    }
}