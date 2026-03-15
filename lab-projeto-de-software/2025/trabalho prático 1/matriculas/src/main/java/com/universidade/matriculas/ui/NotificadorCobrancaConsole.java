package com.universidade.matriculas.ui;

import com.universidade.matriculas.application.NotificadorCobranca;
import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class NotificadorCobrancaConsole implements NotificadorCobranca {

    @Override
    public void notificarMatriculaRealizada(Aluno aluno) {
        Console.println("Sistema de cobranças notificado sobre a matrícula do aluno: " + aluno.getNome());
    }
}
