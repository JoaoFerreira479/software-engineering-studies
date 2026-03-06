package laboratorio.estruturas;

final class NoDuplo<T> {

    private final T elemento;
    private NoDuplo<T> anterior;
    private NoDuplo<T> proximo;

    NoDuplo(T elemento) {
        this.elemento = elemento;
        this.anterior = null;
        this.proximo = null;
    }

    T getElemento() { return elemento; }
    NoDuplo<T> getAnterior() { return anterior; }
    void setAnterior(NoDuplo<T> anterior) { this.anterior = anterior; }
    NoDuplo<T> getProximo() { return proximo; }
    void setProximo(NoDuplo<T> proximo) { this.proximo = proximo; }
}
