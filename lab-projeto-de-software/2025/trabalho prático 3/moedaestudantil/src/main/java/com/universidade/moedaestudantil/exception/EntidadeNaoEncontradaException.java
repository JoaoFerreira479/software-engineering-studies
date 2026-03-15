package com.universidade.moedaestudantil.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(String entidade, Long id) {
        super(entidade + " não encontrado(a) com ID: " + id);
    }
}
