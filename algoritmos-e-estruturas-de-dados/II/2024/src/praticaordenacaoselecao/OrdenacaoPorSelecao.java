package praticaordenacaoselecao;

import java.util.Objects;
import java.util.Scanner;

public final class OrdenacaoPorSelecao {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String linha = scanner.nextLine().trim();
                if (linha.equals("FIM")) break;

                int[] vetor = parseVetor(linha);
                int comparacoes = ordenarPorSelecao(vetor);

                imprimirVetor(vetor);
                System.out.println("Comparacoes realizadas: " + comparacoes);
            }
        }
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

    private static int[] parseVetor(String linha) {
        String[] partes = linha.split(";");
        int[] v = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            v[i] = Integer.parseInt(partes[i].trim());
        }
        return v;
    }

    private static void imprimirVetor(int[] v) {
        for (int i = 0; i < v.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(v[i]);
        }
        System.out.println();
    }
}
