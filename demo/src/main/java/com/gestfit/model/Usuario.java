package com.gestfit.model;

import jakarta.persistence.*;

@Entity
public class Usuario extends Pessoa {
    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    // Métodos
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }
}

