package lista1treino;

import java.util.Objects;

public final class CatalogoOperacoes {

    private static final int CAPACIDADE_INICIAL = 32;

    private final Par[] pares;
    private final int tamanho;

    public CatalogoOperacoes() {
        this.pares = new Par[CAPACIDADE_INICIAL];
        this.tamanho = preencherOperacoes(pares);
    }

    public int tamanho() {
        return tamanho;
    }

    public String padrao(int i) {
        if (i < 0 || i >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + i + ", tamanho: " + tamanho);
        }
        return pares[i].padrao;
    }

    public String descricao(int i) {
        if (i < 0 || i >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + i + ", tamanho: " + tamanho);
        }
        return pares[i].descricao;
    }

    public void paraCada(Consumer consumer) {
        Objects.requireNonNull(consumer, "consumer");
        for (int i = 0; i < tamanho; i++) {
            consumer.aceitar(pares[i].padrao, pares[i].descricao);
        }
    }

    private static int preencherOperacoes(Par[] dest) {
        int i = 0;
        dest[i++] = new Par("\\+", "Adições (+)");
        dest[i++] = new Par("-", "Subtrações (-)");
        dest[i++] = new Par("\\*", "Multiplicações (*)");
        dest[i++] = new Par("/", "Divisões (/)");
        dest[i++] = new Par("%", "Módulos (%)");
        dest[i++] = new Par("==", "Comparações de igualdade (==)");
        dest[i++] = new Par("!=", "Comparações de diferença (!=)");
        dest[i++] = new Par("<", "Comparações menor (<)");
        dest[i++] = new Par("<=", "Comparações menor ou igual (<=)");
        dest[i++] = new Par(">", "Comparações maior (>)");
        dest[i++] = new Par(">=", "Comparações maior ou igual (>=)");
        dest[i++] = new Par("&&", "Operações AND (&&)");
        dest[i++] = new Par("\\|\\|", "Operações OR (||)");
        dest[i++] = new Par("!", "Operações NOT (!)");
        dest[i++] = new Par("if\\s*\\(.*\\)", "Estruturas if");
        dest[i++] = new Par("for\\s*\\(.*\\)", "Loops for");
        dest[i++] = new Par("while\\s*\\(.*\\)", "Loops while");
        dest[i++] = new Par("do\\s*\\{", "Loops do-while");
        dest[i++] = new Par("switch\\s*\\(.*\\)", "Estruturas switch");
        return i;
    }

    @FunctionalInterface
    public interface Consumer {
        void aceitar(String padrao, String descricao);
    }

    private static final class Par {
        final String padrao;
        final String descricao;

        Par(String padrao, String descricao) {
            this.padrao = padrao;
            this.descricao = descricao;
        }
    }
}
