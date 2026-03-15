package aed2.infrastructure.io;


public final class LeituraDados {

    private LeituraDados() {
    }

    public static int[] lerVetorInteiros(EntradaUsuario entrada, int tamanho) {
        int[] v = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            v[i] = entrada.lerInteiro(String.format("Elemento %d: ", i + 1));
        }
        return v;
    }

    public static int[][] lerMatriz(EntradaUsuario entrada, int linhas, int colunas) {
        int[][] m = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m[i][j] = entrada.lerInteiro(String.format("Elemento [%d][%d]: ", i, j));
            }
        }
        return m;
    }
}
