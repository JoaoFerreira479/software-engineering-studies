package aeds2.estruturas;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ABB<E extends Comparable<E>> {

    private NoABB<E> raiz;

    public ABB() {
        this.raiz = null;
    }

    public boolean vazia() {
        return raiz == null;
    }

    public void adicionar(E item) {
        Objects.requireNonNull(item, "item");
        raiz = adicionar(raiz, item);
    }

    private static <E extends Comparable<E>> NoABB<E> adicionar(NoABB<E> no, E item) {
        if (no == null) {
            return new NoABB<>(item);
        }
        int cmp = item.compareTo(no.getItem());
        if (cmp < 0) {
            no.setEsquerda(adicionar(no.getEsquerda(), item));
        } else if (cmp > 0) {
            no.setDireita(adicionar(no.getDireita(), item));
        }
        return no;
    }

    public boolean contem(E item) {
        return buscar(raiz, item) != null;
    }

    public E pesquisar(E procurado) {
        Objects.requireNonNull(procurado, "procurado");
        NoABB<E> no = buscar(raiz, procurado);
        if (no == null) {
            throw new NoSuchElementException("Elemento nao encontrado");
        }
        return no.getItem();
    }

    private static <E extends Comparable<E>> NoABB<E> buscar(NoABB<E> no, E procurado) {
        if (no == null) {
            return null;
        }
        int cmp = procurado.compareTo(no.getItem());
        if (cmp == 0) {
            return no;
        }
        if (cmp < 0) {
            return buscar(no.getEsquerda(), procurado);
        }
        return buscar(no.getDireita(), procurado);
    }

    public void remover(E item) {
        Objects.requireNonNull(item, "item");
        raiz = remover(raiz, item);
    }

    private static <E extends Comparable<E>> NoABB<E> remover(NoABB<E> no, E item) {
        if (no == null) {
            throw new NoSuchElementException("Item nao encontrado na arvore");
        }
        int cmp = item.compareTo(no.getItem());
        if (cmp < 0) {
            no.setEsquerda(remover(no.getEsquerda(), item));
            return no;
        }
        if (cmp > 0) {
            no.setDireita(remover(no.getDireita(), item));
            return no;
        }
        if (no.getEsquerda() == null) {
            return no.getDireita();
        }
        if (no.getDireita() == null) {
            return no.getEsquerda();
        }
        NoABB<E> sucessor = menor(no.getDireita());
        NoABB<E> novaRaiz = new NoABB<>(sucessor.getItem());
        novaRaiz.setEsquerda(no.getEsquerda());
        novaRaiz.setDireita(remover(no.getDireita(), sucessor.getItem()));
        return novaRaiz;
    }

    private static <E extends Comparable<E>> NoABB<E> menor(NoABB<E> no) {
        NoABB<E> atual = no;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public void emOrdem(Consumer<E> visitante) {
        emOrdem(raiz, visitante);
    }

    private static <E extends Comparable<E>> void emOrdem(NoABB<E> no, Consumer<E> visitante) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsquerda(), visitante);
        visitante.accept(no.getItem());
        emOrdem(no.getDireita(), visitante);
    }

    protected NoABB<E> getRaiz() {
        return raiz;
    }

    @FunctionalInterface
    public interface Consumer<E> {
        void accept(E e);
    }
}
