package aeds2.estruturas;

import java.util.Objects;

public class ListaDuplamenteEncadeada<E> {

    private CelulaDupla<E> primeiro;
    private CelulaDupla<E> ultimo;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public boolean vazia() {
        return primeiro == null;
    }

    public void inserirFinal(E item) {
        Objects.requireNonNull(item, "item");
        CelulaDupla<E> nova = new CelulaDupla<>(item);
        if (vazia()) {
            primeiro = ultimo = nova;
        } else {
            ultimo.setProximo(nova);
            nova.setAnterior(ultimo);
            ultimo = nova;
        }
    }

    public boolean contemSequencia(ListaEncadeada<E> sequencia) {
        if (sequencia.vazia()) {
            return true;
        }
        CelulaDupla<E> atual = primeiro;
        while (atual != null) {
            if (comecaEm(atual, sequencia.getPrimeiro())) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    private static <E> boolean comecaEm(CelulaDupla<E> no, Celula<E> seqInicio) {
        CelulaDupla<E> p = no;
        Celula<E> q = seqInicio;
        while (p != null && q != null) {
            if (!Objects.equals(p.getItem(), q.getItem())) {
                return false;
            }
            p = p.getProximo();
            q = q.getProximo();
        }
        return q == null;
    }

    public void mesclar(ListaEncadeada<E> outros) {
        Celula<E> atual = outros.getPrimeiro();
        while (atual != null) {
            inserirFinal(atual.getItem());
            atual = atual.getProximo();
        }
    }

    public void paraCada(ABB.Consumer<E> visitante) {
        CelulaDupla<E> atual = primeiro;
        while (atual != null) {
            visitante.accept(atual.getItem());
            atual = atual.getProximo();
        }
    }
}
