package com.gestfit.repository;

import com.gestfit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByCpf(String cpf);
}