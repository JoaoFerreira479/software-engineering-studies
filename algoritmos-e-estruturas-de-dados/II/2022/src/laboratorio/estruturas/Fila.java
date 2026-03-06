package laboratorio.estruturas;

public final class Fila<T> {

    private final ListaSequencial<T> lista;

    public Fila(int capacidade) {
        this.lista = new ListaSequencial<>(capacidade);
    }

    public void enqueue(T elemento) {
        lista.inserirUltimaPosicao(elemento);
    }

    public T dequeue() {
        return lista.removerPrimeiraPosicao();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int tamanho() {
        return lista.tamanho();
    }
}
