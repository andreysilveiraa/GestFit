package com.gestfit.service;

import com.gestfit.model.LogAuditoria;
import com.gestfit.model.TipoOperacao;
import com.gestfit.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogAuditoriaRepository logAuditoriaRepository;

    // Método correspondente ao registrarAcao do seu diagrama
    public void registrarAcao(String usuarioResponsavel, TipoOperacao operacao, String detalhes) {
        LogAuditoria log = new LogAuditoria(usuarioResponsavel, operacao, detalhes);
        logAuditoriaRepository.save(log);
    }

    // Método correspondente ao consultarLogsPorData do seu diagrama
    public List<LogAuditoria> consultarLogsPorData(LocalDateTime inicio, LocalDateTime fim) {
        return logAuditoriaRepository.findByTimestampBetween(inicio, fim);
    }
}