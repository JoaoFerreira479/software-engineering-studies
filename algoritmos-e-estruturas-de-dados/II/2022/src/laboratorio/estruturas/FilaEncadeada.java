package laboratorio.estruturas;

import java.util.Objects;

public final class FilaEncadeada<T> {

    private NoEncadeado<T> frente;
    private NoEncadeado<T> traseira;
    private int tamanho;

    public FilaEncadeada() {
        frente = null;
        traseira = null;
        tamanho = 0;
    }

    public void enfileirar(T item) {
        Objects.requireNonNull(item, "item");
        NoEncadeado<T> novo = new NoEncadeado<>(item);
        if (traseira == null) {
            frente = traseira = novo;
        } else {
            traseira.setProximo(novo);
            traseira = novo;
        }
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        T removido = frente.getElemento();
        frente = frente.getProximo();
        if (frente == null) traseira = null;
        tamanho--;
        return removido;
    }

    public T frente() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        return frente.getElemento();
    }

    public T traseira() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        return traseira.getElemento();
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public void limpar() {
        frente = null;
        traseira = null;
        tamanho = 0;
    }
}
