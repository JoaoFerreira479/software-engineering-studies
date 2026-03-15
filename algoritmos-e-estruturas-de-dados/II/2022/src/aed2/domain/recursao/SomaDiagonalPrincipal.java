package aed2.domain.recursao;

public final class SomaDiagonalPrincipal {

    private SomaDiagonalPrincipal() {}

    public static int calcularSomaDiagonalPrincipal(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz inválida.");
        }
        int soma = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i] == null || matriz[i].length != matriz.length) {
                throw new IllegalArgumentException("Matriz deve ser quadrada.");
            }
            soma += matriz[i][i];
        }
        return soma;
    }
}
