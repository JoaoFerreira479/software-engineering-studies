package laboratorio.estruturas;

import java.util.Objects;

public final class ListaSequencial<T> {

    private final Object[] elementos;
    private int tamanho;

    public ListaSequencial(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva: " + capacidade);
        }
        this.elementos = new Object[capacidade];
        this.tamanho = 0;
    }

    public void inserirPrimeiraPosicao(T x) {
        Objects.requireNonNull(x, "Elemento não pode ser nulo");
        garantirCapacidade();
        deslocarDireita(0);
        elementos[0] = x;
        tamanho++;
    }

    public void inserirUltimaPosicao(T x) {
        Objects.requireNonNull(x, "Elemento não pode ser nulo");
        garantirCapacidade();
        elementos[tamanho++] = x;
    }

    public void inserirNaPosicao(T x, int pos) {
        Objects.requireNonNull(x, "Elemento não pode ser nulo");
        if (pos < 0 || pos > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        garantirCapacidade();
        deslocarDireita(pos);
        elementos[pos] = x;
        tamanho++;
    }

    public T removerPrimeiraPosicao() {
        if (tamanho == 0) {
            throw new IllegalStateException("Lista vazia");
        }
        @SuppressWarnings("unchecked")
        T removido = (T) elementos[0];
        deslocarEsquerda(0);
        tamanho--;
        return removido;
    }

    public T removerUltimaPosicao() {
        if (tamanho == 0) {
            throw new IllegalStateException("Lista vazia");
        }
        tamanho--;
        @SuppressWarnings("unchecked")
        T removido = (T) elementos[tamanho];
        elementos[tamanho] = null;
        return removido;
    }

    public T removerNaPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        @SuppressWarnings("unchecked")
        T removido = (T) elementos[pos];
        deslocarEsquerda(pos);
        tamanho--;
        return removido;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    @SuppressWarnings("unchecked")
    public T obter(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos + ", tamanho: " + tamanho);
        }
        return (T) elementos[pos];
    }

    public void percorrer(Consumer<T> acao) {
        for (int i = 0; i < tamanho; i++) {
            acao.aceitar(obter(i));
        }
    }

    private void garantirCapacidade() {
        if (tamanho >= elementos.length) {
            throw new IllegalStateException("Lista cheia (capacidade " + elementos.length + ")");
        }
    }

    private void deslocarDireita(int aPartirDe) {
        for (int i = tamanho; i > aPartirDe; i--) {
            elementos[i] = elementos[i - 1];
        }
    }

    private void deslocarEsquerda(int aPartirDe) {
        for (int i = aPartirDe; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[tamanho - 1] = null;
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void aceitar(T t);
    }
}
