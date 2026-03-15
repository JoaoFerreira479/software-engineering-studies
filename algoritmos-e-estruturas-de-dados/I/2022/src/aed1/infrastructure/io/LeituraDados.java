package aed1.infrastructure.io;


public final class LeituraDados {

    private LeituraDados() {
    }

    public static int[] lerVetor(final EntradaUsuario entrada, final int tamanho) {
        int[] v = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            v[i] = entrada.lerInteiro(String.format("Valor posição %d: ", i));
        }
        return v;
    }

    public static int[] lerVetor(final EntradaUsuario entrada, final int tamanho, final String nomeVetor) {
        Console.printf("Digite os valores do %s vetor:%n", nomeVetor);
        return lerVetor(entrada, tamanho);
    }

    public static int[][] lerMatriz(final EntradaUsuario entrada, final int linhas, final int colunas) {
        int[][] m = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m[i][j] = entrada.lerInteiro(String.format("Valor [%d][%d]: ", i, j));
            }
        }
        return m;
    }

    public static int[][] lerMatrizQuadrada(final EntradaUsuario entrada, final int n) {
        Console.println("Digite os valores da matriz:");
        return lerMatriz(entrada, n, n);
    }
}
