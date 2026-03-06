package aeds2.estruturas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Fila<E> {

    private Celula<E> frente;
    private Celula<E> tras;

    public Fila() {
        Celula<E> sentinela = new Celula<>(null);
        this.frente = sentinela;
        this.tras = sentinela;
    }

    public boolean vazia() {
        return frente == tras;
    }

    public void enfileirar(E item) {
        Objects.requireNonNull(item, "item");
        tras.setProximo(new Celula<>(item));
        tras = tras.getProximo();
    }

    public E desenfileirar() {
        if (vazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        Celula<E> primeira = frente.getProximo();
        frente.setProximo(primeira.getProximo());
        if (tras == primeira) {
            tras = frente;
        }
        return primeira.getItem();
    }

    public boolean contem(E item) {
        Objects.requireNonNull(item, "item");
        Celula<E> atual = frente.getProximo();
        while (atual != null) {
            if (item.equals(atual.getItem())) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public Fila<E> dividir() {
        Fila<E> novaFila = new Fila<>();
        Fila<E> original = new Fila<>();
        Celula<E> atual = frente.getProximo();
        boolean impar = true;
        while (atual != null) {
            if (impar) {
                original.enfileirar(atual.getItem());
            } else {
                novaFila.enfileirar(atual.getItem());
            }
            atual = atual.getProximo();
            impar = !impar;
        }
        this.frente = original.frente;
        this.tras = original.tras;
        return novaFila;
    }

    public void paraCada(ABB.Consumer<E> visitante) {
        Celula<E> atual = frente.getProximo();
        while (atual != null) {
            visitante.accept(atual.getItem());
            atual = atual.getProximo();
        }
    }
}
