package estruturasrepeticao.domain;

public final class EstruturasRepeticao {

    private EstruturasRepeticao() {
    }

    public static int[] sequenciaInclusiva(final int inicio, final int fim) {
        if (inicio > fim) {
            throw new IllegalArgumentException("Início deve ser menor ou igual ao fim.");
        }
        int n = fim - inicio + 1;
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = inicio + i;
        }
        return seq;
    }
}
