package aed2.domain.ordenacao;

import java.util.Objects;

public final class OrdenacaoPorSelecao {

    private OrdenacaoPorSelecao() {
    }

    public static int ordenarPorSelecao(int[] vetor) {
        Objects.requireNonNull(vetor, "vetor");
        int comparacoes = 0;
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            trocar(vetor, i, indiceMenor);
        }
        return comparacoes;
    }

    private static void trocar(int[] v, int i, int j) {
        int t = v[i];
        v[i] = v[j];
        v[j] = t;
    }
}
