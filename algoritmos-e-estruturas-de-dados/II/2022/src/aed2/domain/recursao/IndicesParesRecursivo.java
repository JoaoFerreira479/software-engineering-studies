package aed2.domain.recursao;

public final class IndicesParesRecursivo {

    private IndicesParesRecursivo() {}

    
    public static int[] valoresNosIndicesPares(int[] vetor) {
        if (vetor == null) return new int[0];
        int count = (vetor.length + 1) / 2;
        int[] resultado = new int[count];
        coletarPares(vetor, 0, resultado, 0);
        return resultado;
    }

    private static void coletarPares(int[] vetor, int indice, int[] dest, int pos) {
        if (indice >= vetor.length) return;
        if (indice % 2 == 0) {
            dest[pos] = vetor[indice];
            coletarPares(vetor, indice + 1, dest, pos + 1);
        } else {
            coletarPares(vetor, indice + 1, dest, pos);
        }
    }
}
