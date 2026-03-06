package laboratorio.estruturas;

import java.util.Objects;

public final class PilhaEncadeada<T> {

    private NoEncadeado<T> topo;
    private int tamanho;

    public PilhaEncadeada() {
        topo = null;
        tamanho = 0;
    }

    public void empilhar(T elemento) {
        Objects.requireNonNull(elemento, "elemento");
        NoEncadeado<T> nova = new NoEncadeado<>(elemento);
        nova.setProximo(topo);
        topo = nova;
        tamanho++;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("Pilha vazia");
        }
        T removido = topo.getElemento();
        topo = topo.getProximo();
        tamanho--;
        return removido;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public double somarElementosComoDouble() {
        double soma = 0;
        NoEncadeado<T> cur = topo;
        while (cur != null) {
            Object el = cur.getElemento();
            if (el instanceof Number n) {
                soma += n.doubleValue();
            } else {
                throw new UnsupportedOperationException("Soma suportada apenas para elementos numéricos");
            }
            cur = cur.getProximo();
        }
        return soma;
    }

    public void percorrer(ListaSequencial.Consumer<T> acao) {
        NoEncadeado<T> cur = topo;
        while (cur != null) {
            acao.aceitar(cur.getElemento());
            cur = cur.getProximo();
        }
    }
}
