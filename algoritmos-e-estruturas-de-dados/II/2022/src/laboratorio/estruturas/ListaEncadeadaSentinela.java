package laboratorio.estruturas;

import java.util.Objects;

public final class ListaEncadeadaSentinela<T> {

    private final NoSentinela<T> sentinela;
    private NoSentinela<T> ultimo;
    private int tamanho;

    public ListaEncadeadaSentinela() {
        this.sentinela = new NoSentinela<>();
        this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public void inserirInicio(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoSentinela<T> nova = new NoSentinela<>(elemento);
        nova.setProximo(sentinela.getProximo());
        sentinela.setProximo(nova);
        if (tamanho == 0) ultimo = nova;
        tamanho++;
    }

    public void inserirFim(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoSentinela<T> nova = new NoSentinela<>(elemento);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public T removerInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("Lista vazia");
        }
        NoSentinela<T> removida = sentinela.getProximo();
        sentinela.setProximo(removida.getProximo());
        if (sentinela.getProximo() == null) ultimo = sentinela;
        tamanho--;
        return removida.getElemento();
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
        NoSentinela<T> cur = sentinela.getProximo();
        for (int i = 0; i < pos; i++) {
            cur = cur.getProximo();
        }
        return cur.getElemento();
    }

    public void percorrer(ListaSequencial.Consumer<T> acao) {
        NoSentinela<T> cur = sentinela.getProximo();
        while (cur != null) {
            acao.aceitar(cur.getElemento());
            cur = cur.getProximo();
        }
    }
}
