package laboratorio.estruturas;

final class NoEncadeado<T> {

    private final T elemento;
    private NoEncadeado<T> proximo;

    NoEncadeado(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    T getElemento() { return elemento; }
    NoEncadeado<T> getProximo() { return proximo; }
    void setProximo(NoEncadeado<T> proximo) { this.proximo = proximo; }
}
