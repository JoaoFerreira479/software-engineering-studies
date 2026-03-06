package laboratorio.estruturas;

import java.util.Objects;

public final class ListaEncadeada<T> {

    private NoEncadeado<T> inicio;
    private NoEncadeado<T> fim;
    private int tamanho;

    public ListaEncadeada() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    public void inserirInicio(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoEncadeado<T> nova = new NoEncadeado<>(elemento);
        if (inicio == null) {
            inicio = fim = nova;
        } else {
            nova.setProximo(inicio);
            inicio = nova;
        }
        tamanho++;
    }

    public void inserirFim(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoEncadeado<T> nova = new NoEncadeado<>(elemento);
        if (fim == null) {
            inicio = fim = nova;
        } else {
            fim.setProximo(nova);
            fim = nova;
        }
        tamanho++;
    }

    public void inserirNaPosicao(T elemento, int pos) {
        Objects.requireNonNull(elemento, "elemento");
        if (pos < 0 || pos > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        if (pos == 0) {
            inserirInicio(elemento);
            return;
        }
        if (pos == tamanho) {
            inserirFim(elemento);
            return;
        }
        NoEncadeado<T> nova = new NoEncadeado<>(elemento);
        NoEncadeado<T> atual = inicio;
        for (int i = 0; i < pos - 1; i++) {
            atual = atual.getProximo();
        }
        nova.setProximo(atual.getProximo());
        atual.setProximo(nova);
        tamanho++;
    }

    public T removerInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        }
        T removido = inicio.getElemento();
        inicio = inicio.getProximo();
        if (inicio == null) fim = null;
        tamanho--;
        return removido;
    }

    public T removerFim() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        }
        if (inicio == fim) {
            T removido = inicio.getElemento();
            inicio = fim = null;
            tamanho--;
            return removido;
        }
        NoEncadeado<T> ant = inicio;
        while (ant.getProximo() != fim) {
            ant = ant.getProximo();
        }
        T removido = fim.getElemento();
        fim = ant;
        fim.setProximo(null);
        tamanho--;
        return removido;
    }

    public T removerNaPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        if (pos == 0) return removerInicio();
        if (pos == tamanho - 1) return removerFim();
        NoEncadeado<T> ant = inicio;
        for (int i = 0; i < pos - 1; i++) {
            ant = ant.getProximo();
        }
        T removido = ant.getProximo().getElemento();
        ant.setProximo(ant.getProximo().getProximo());
        tamanho--;
        return removido;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public T obter(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        NoEncadeado<T> cur = inicio;
        for (int i = 0; i < pos; i++) {
            cur = cur.getProximo();
        }
        return cur.getElemento();
    }

    public void percorrer(ListaSequencial.Consumer<T> acao) {
        NoEncadeado<T> cur = inicio;
        while (cur != null) {
            acao.aceitar(cur.getElemento());
            cur = cur.getProximo();
        }
    }
}
