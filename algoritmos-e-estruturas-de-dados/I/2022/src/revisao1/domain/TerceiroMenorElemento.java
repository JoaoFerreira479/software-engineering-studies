package revisao1.domain;

public final class TerceiroMenorElemento {

    private TerceiroMenorElemento() {
    }

    public static void validarDimensoes(final int linhas, final int colunas) {
        if (linhas * colunas < 3) {
            throw new IllegalArgumentException("A matriz deve ter pelo menos 3 elementos.");
        }
    }

    public static int terceiroMenor(final int[][] matriz) {
        int[] vetor = transformarEmVetor(matriz);
        ordenarPorSelecao(vetor);
        return vetor[2];
    }

    static int[] transformarEmVetor(final int[][] matriz) {
        int total = 0;
        for (int[] linha : matriz) {
            total += linha.length;
        }
        int[] v = new int[total];
        int k = 0;
        for (int[] linha : matriz) {
            for (int x : linha) {
                v[k++] = x;
            }
        }
        return v;
    }

    private static void ordenarPorSelecao(final int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[minIdx]) minIdx = j;
            }
            int t = v[i];
            v[i] = v[minIdx];
            v[minIdx] = t;
        }
    }
}
