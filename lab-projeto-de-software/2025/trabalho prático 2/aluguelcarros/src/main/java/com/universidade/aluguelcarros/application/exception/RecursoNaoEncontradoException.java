package com.universidade.aluguelcarros.application.exception;

public final class RecursoNaoEncontradoException extends RuntimeException {

    private final String recurso;
    private final Object identificador;

    public RecursoNaoEncontradoException(String recurso, Object identificador) {
        super(String.format("%s não encontrado(a) com identificador: %s", recurso, identificador));
        this.recurso = recurso;
        this.identificador = identificador;
    }

    public String getRecurso() {
        return recurso;
    }

    public Object getIdentificador() {
        return identificador;
    }
}
