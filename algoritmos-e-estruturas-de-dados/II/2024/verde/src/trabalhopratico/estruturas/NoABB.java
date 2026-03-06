package trabalhopratico.estruturas;

public final class NoABB<E extends Comparable<E>> {

    private final E item;
    private NoABB<E> esquerda;
    private NoABB<E> direita;

    public NoABB(E item) {
        this.item = item;
        this.esquerda = null;
        this.direita = null;
    }

    public E getItem() {
        return item;
    }

    public NoABB<E> getEsquerda() {
        return esquerda;
    }

    public NoABB<E> getDireita() {
        return direita;
    }

    public void setEsquerda(NoABB<E> esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(NoABB<E> direita) {
        this.direita = direita;
    }
}
