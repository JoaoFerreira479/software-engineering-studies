package aeds2.estruturas;

public final class CelulaDupla<E> {

    private final E item;
    private CelulaDupla<E> anterior;
    private CelulaDupla<E> proximo;

    public CelulaDupla(E item) {
        this.item = item;
        this.anterior = null;
        this.proximo = null;
    }

    public E getItem() {
        return item;
    }

    public CelulaDupla<E> getAnterior() {
        return anterior;
    }

    public CelulaDupla<E> getProximo() {
        return proximo;
    }

    public void setAnterior(CelulaDupla<E> anterior) {
        this.anterior = anterior;
    }

    public void setProximo(CelulaDupla<E> proximo) {
        this.proximo = proximo;
    }
}
