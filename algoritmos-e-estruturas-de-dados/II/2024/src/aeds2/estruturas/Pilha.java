package aeds2.estruturas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Pilha<E> {

    private final Celula<E> fundo;
    private Celula<E> topo;

    public Pilha() {
        Celula<E> sentinela = new Celula<>(null);
        this.fundo = sentinela;
        this.topo = sentinela;
    }

    public boolean vazia() {
        return fundo == topo;
    }

    public void empilhar(E item) {
        Objects.requireNonNull(item, "item");
        topo = new Celula<>(item, topo);
    }

    public E desempilhar() {
        E item = consultarTopo();
        topo = topo.getProximo();
        return item;
    }

    public E consultarTopo() {
        if (vazia()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return topo.getItem();
    }
}
