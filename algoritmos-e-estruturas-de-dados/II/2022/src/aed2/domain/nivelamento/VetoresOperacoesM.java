package aed2.domain.nivelamento;

public final class VetoresOperacoesM {

    private VetoresOperacoesM() {}

    public static int[] somarVetores(int[] x, int[] y) {
        if (x == null || y == null || x.length != y.length) {
            throw new IllegalArgumentException("Vetores devem ter o mesmo tamanho.");
        }
        int[] soma = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            soma[i] = x[i] + y[i];
        }
        return soma;
    }

    public static int[] produtoVetores(int[] x, int[] y) {
        if (x == null || y == null || x.length != y.length) {
            throw new IllegalArgumentException("Vetores devem ter o mesmo tamanho.");
        }
        int[] produto = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            produto[i] = x[i] * y[i];
        }
        return produto;
    }

    public static String vetorParaString(int[] vetor) {
        if (vetor == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vetor.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(vetor[i]);
        }
        return sb.toString();
    }
}
