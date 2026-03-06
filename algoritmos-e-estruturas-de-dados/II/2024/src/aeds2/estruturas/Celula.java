package aeds2.estruturas;

public final class Celula<E> {

    private final E item;
    private Celula<E> proximo;

    public Celula(E item) {
        this.item = item;
        this.proximo = null;
    }

    public Celula(E item, Celula<E> proximo) {
        this.item = item;
        this.proximo = proximo;
    }

    public E getItem() {
        return item;
    }

    public Celula<E> getProximo() {
        return proximo;
    }

    public void setProximo(Celula<E> proximo) {
        this.proximo = proximo;
    }
}
