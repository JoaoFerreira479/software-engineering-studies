package laboratorio.estruturas;

import java.util.Objects;

public final class ListaDuplamenteEncadeada<T> {

    private final NoDuplo<T> sentinela;
    private NoDuplo<T> ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.sentinela = new NoDuplo<>(null);
        sentinela.setProximo(null);
        this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public void inserirInicio(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoDuplo<T> nova = new NoDuplo<>(elemento);
        nova.setProximo(sentinela.getProximo());
        nova.setAnterior(sentinela);
        if (sentinela.getProximo() != null) {
            sentinela.getProximo().setAnterior(nova);
        } else {
            ultimo = nova;
        }
        sentinela.setProximo(nova);
        tamanho++;
    }

    public void inserirFim(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoDuplo<T> nova = new NoDuplo<>(elemento);
        nova.setAnterior(ultimo);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public T removerInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        }
        NoDuplo<T> removida = sentinela.getProximo();
        sentinela.setProximo(removida.getProximo());
        if (removida.getProximo() != null) {
            removida.getProximo().setAnterior(sentinela);
        } else {
            ultimo = sentinela;
        }
        tamanho--;
        return removida.getElemento();
    }

    public T removerFim() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        }
        NoDuplo<T> removida = ultimo;
        ultimo = removida.getAnterior();
        ultimo.setProximo(null);
        tamanho--;
        return removida.getElemento();
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void percorrerDoInicio(ListaSequencial.Consumer<T> acao) {
        NoDuplo<T> cur = sentinela.getProximo();
        while (cur != null) {
            acao.aceitar(cur.getElemento());
            cur = cur.getProximo();
        }
    }

    public void percorrerDoFim(ListaSequencial.Consumer<T> acao) {
        NoDuplo<T> cur = ultimo;
        while (cur != sentinela) {
            acao.aceitar(cur.getElemento());
            cur = cur.getAnterior();
        }
    }
}
