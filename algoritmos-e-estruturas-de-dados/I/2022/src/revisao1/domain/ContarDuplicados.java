package revisao1.domain;

public final class ContarDuplicados {

    private ContarDuplicados() {
    }

    public static void validarTamanho(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("O vetor deve ter pelo menos 1 elemento.");
        }
    }

    public static int contar(final int[] vetor) {
        int resultado = 0;
        for (int i = 0; i < vetor.length; i++) {
            if (!apareceAntes(vetor, i)) {
                int ocorrencias = contarOcorrencias(vetor, vetor[i]);
                if (ocorrencias > 1) {
                    resultado++;
                }
            }
        }
        return resultado;
    }

    private static boolean apareceAntes(final int[] v, final int indice) {
        for (int j = 0; j < indice; j++) {
            if (v[j] == v[indice]) return true;
        }
        return false;
    }

    private static int contarOcorrencias(final int[] v, final int valor) {
        int count = 0;
        for (int x : v) {
            if (x == valor) count++;
        }
        return count;
    }
}
