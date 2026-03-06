package praticarecursividade;

import java.util.Objects;
import java.util.Scanner;

public final class ContaOcorrencias {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String linhaVetor = scanner.nextLine().trim();
                if (linhaVetor.equals("FIM")) break;

                int[] vetor = parseVetor(linhaVetor);
                int x = Integer.parseInt(scanner.nextLine().trim());
                System.out.println(contaOcorrencias(vetor, x));
            }
        }
    }

    public static int contaOcorrencias(int[] vetor, int x) {
        Objects.requireNonNull(vetor, "vetor");
        return contaOcorrenciasRec(vetor, x, vetor.length - 1);
    }

    private static int contaOcorrenciasRec(int[] vetor, int x, int index) {
        if (index < 0) return 0;
        return (vetor[index] == x ? 1 : 0) + contaOcorrenciasRec(vetor, x, index - 1);
    }

    private static int[] parseVetor(String linha) {
        String[] partes = linha.split(";");
        int[] v = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            v[i] = Integer.parseInt(partes[i].trim());
        }
        return v;
    }
}
