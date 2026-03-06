package laboratorio.estruturas;

public final class Pilha<T> {

    private final ListaSequencial<T> lista;

    public Pilha(int capacidade) {
        this.lista = new ListaSequencial<>(capacidade);
    }

    public void push(T elemento) {
        lista.inserirUltimaPosicao(elemento);
    }

    public T pop() {
        return lista.removerUltimaPosicao();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int tamanho() {
        return lista.tamanho();
    }
}
