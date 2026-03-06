package laboratorio.estruturas;

import java.util.Objects;

public final class ListaOrdenada {

    private static final int CAPACIDADE_INICIAL = 16;

    private String[] elementos;
    private int tamanho;

    public ListaOrdenada() {
        this.elementos = new String[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }

    public void adicionar(String nome) {
        String n = Objects.requireNonNull(nome, "nome").trim();
        if (n.isEmpty()) return;
        garantirCapacidade();
        int pos = indiceInsercao(n);
        deslocarDireita(pos);
        elementos[pos] = n;
        tamanho++;
    }

    public void adicionarNaPosicao(String nome, int posicao) {
        String n = Objects.requireNonNull(nome, "nome").trim();
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao + ", tamanho: " + tamanho);
        }
        garantirCapacidade();
        deslocarDireita(posicao);
        elementos[posicao] = n;
        tamanho++;
        ordenar();
    }

    public void remover(String nome) {
        String n = Objects.requireNonNull(nome, "nome").trim();
        int idx = buscaBinariaIndice(n);
        if (idx >= 0) {
            deslocarEsquerda(idx);
            tamanho--;
        }
    }

    public boolean buscaSequencial(String nome) {
        String n = Objects.requireNonNull(nome, "nome").trim();
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].equalsIgnoreCase(n)) return true;
        }
        return false;
    }

    public boolean buscaBinaria(String nome) {
        return buscaBinariaIndice(Objects.requireNonNull(nome, "nome").trim()) >= 0;
    }

    private int buscaBinariaIndice(String nome) {
        int esq = 0;
        int dir = tamanho - 1;
        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            int cmp = elementos[meio].compareToIgnoreCase(nome);
            if (cmp == 0) return meio;
            if (cmp < 0) esq = meio + 1;
            else dir = meio - 1;
        }
        return -1;
    }

    private int indiceInsercao(String nome) {
        int esq = 0;
        int dir = tamanho;
        while (esq < dir) {
            int meio = esq + (dir - esq) / 2;
            if (elementos[meio].compareToIgnoreCase(nome) < 0) {
                esq = meio + 1;
            } else {
                dir = meio;
            }
        }
        return esq;
    }

    private void ordenar() {
        for (int i = 1; i < tamanho; i++) {
            String chave = elementos[i];
            int j = i - 1;
            while (j >= 0 && elementos[j].compareToIgnoreCase(chave) > 0) {
                elementos[j + 1] = elementos[j];
                j--;
            }
            elementos[j + 1] = chave;
        }
    }

    public String obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice + ", tamanho: " + tamanho);
        }
        return elementos[indice];
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void percorrer(ListaSequencial.Consumer<String> acao) {
        for (int i = 0; i < tamanho; i++) {
            acao.aceitar(elementos[i]);
        }
    }

    private void garantirCapacidade() {
        if (tamanho >= elementos.length) {
            int novaCap = elementos.length * 2;
            String[] novo = new String[novaCap];
            for (int i = 0; i < tamanho; i++) {
                novo[i] = elementos[i];
            }
            elementos = novo;
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
}
