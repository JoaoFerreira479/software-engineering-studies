package aeds2.estruturas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaEncadeada<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public boolean vazia() {
        return primeiro == null;
    }

    public void inserirInicio(E item) {
        Objects.requireNonNull(item, "item");
        Celula<E> nova = new Celula<>(item, primeiro);
        primeiro = nova;
        if (ultimo == null) {
            ultimo = nova;
        }
    }

    public void inserirFinal(E item) {
        Objects.requireNonNull(item, "item");
        Celula<E> nova = new Celula<>(item);
        if (vazia()) {
            primeiro = ultimo = nova;
        } else {
            ultimo.setProximo(nova);
            ultimo = nova;
        }
    }

    public E removerInicio() {
        if (vazia()) {
            throw new NoSuchElementException("Lista vazia");
        }
        E item = primeiro.getItem();
        primeiro = primeiro.getProximo();
        if (primeiro == null) {
            ultimo = null;
        }
        return item;
    }

    public void paraCada(ABB.Consumer<E> visitante) {
        Celula<E> atual = primeiro;
        while (atual != null) {
            visitante.accept(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public void paraCadaReverso(ABB.Consumer<E> visitante) {
        paraCadaReverso(primeiro, visitante);
    }

    private static <E> void paraCadaReverso(Celula<E> celula, ABB.Consumer<E> visitante) {
        if (celula == null) {
            return;
        }
        paraCadaReverso(celula.getProximo(), visitante);
        visitante.accept(celula.getItem());
    }

    public Celula<E> getPrimeiro() {
        return primeiro;
    }
}
