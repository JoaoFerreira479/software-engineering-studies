package laboratorio.estruturas;

final class NoSentinela<T> {

    private T elemento;
    private NoSentinela<T> proximo;

    NoSentinela() {
        this.elemento = null;
        this.proximo = null;
    }

    NoSentinela(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    T getElemento() { return elemento; }
    void setElemento(T elemento) { this.elemento = elemento; }
    NoSentinela<T> getProximo() { return proximo; }
    void setProximo(NoSentinela<T> proximo) { this.proximo = proximo; }
}
