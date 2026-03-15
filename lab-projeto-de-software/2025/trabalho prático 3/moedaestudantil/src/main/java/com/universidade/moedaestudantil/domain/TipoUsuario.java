package com.universidade.moedaestudantil.domain;

import com.universidade.moedaestudantil.model.Aluno;
import com.universidade.moedaestudantil.model.EmpresaParceira;
import com.universidade.moedaestudantil.model.Professor;
import com.universidade.moedaestudantil.model.Usuario;

public enum TipoUsuario {
    USER,
    ALUNO,
    PROFESSOR,
    EMPRESA;

    public static TipoUsuario from(Usuario usuario) {
        if (usuario == null) return USER;
        if (usuario instanceof Aluno) return ALUNO;
        if (usuario instanceof Professor) return PROFESSOR;
        if (usuario instanceof EmpresaParceira) return EMPRESA;
        return USER;
    }
}
