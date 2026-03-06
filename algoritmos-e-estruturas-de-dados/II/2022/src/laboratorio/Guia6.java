package laboratorio;

import laboratorio.algoritmos.Ordenacao;

import java.util.Random;

public final class Guia6 {

    private Guia6() {}

    public static void main(String[] args) {
        int tamanho = 1_000_000;
        if (tamanho <= 0) {
            System.err.println("Tamanho inválido.");
            return;
        }

        System.out.println("Gerando vetor aleatório...");
        long inicio = System.currentTimeMillis();
        double[] vetor = geraAleatorio(tamanho);
        long fim = System.currentTimeMillis();
        System.out.printf("Tempo para gerar vetor: %.5f segundos%n", (fim - inicio) / 1000.0);

        exibirAmostra(vetor, "Vetor gerado", 10);

        System.out.println("\nOrdenando com QuickSort...");
        double[] copiaQuickSort = copiarVetor(vetor);
        inicio = System.currentTimeMillis();
        Ordenacao.quickSort(copiaQuickSort, 0, copiaQuickSort.length - 1);
        fim = System.currentTimeMillis();
        System.out.printf("Tempo para QuickSort: %.5f segundos%n", (fim - inicio) / 1000.0);
        exibirAmostra(copiaQuickSort, "Vetor ordenado com QuickSort", 10);

        System.out.println("\nOrdenando com HeapSort...");
        double[] copiaHeapSort = copiarVetor(vetor);
        inicio = System.currentTimeMillis();
        Ordenacao.heapSort(copiaHeapSort);
        fim = System.currentTimeMillis();
        System.out.printf("Tempo para HeapSort: %.5f segundos%n", (fim - inicio) / 1000.0);
        exibirAmostra(copiaHeapSort, "Vetor ordenado com HeapSort", 10);
    }

    public static double[] geraAleatorio(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("Tamanho deve ser positivo: " + tamanho);
        }
        Random random = new Random();
        double[] vetor = new double[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextDouble();
        }
        return vetor;
    }

    public static void exibirAmostra(double[] vetor, String descricao, int quantidade) {
        if (vetor == null) return;
        int n = Math.min(quantidade, vetor.length);
        System.out.printf("%n%s (primeiros %d elementos):%n", descricao, n);
        for (int i = 0; i < n; i++) {
            System.out.printf("%.5f ", vetor[i]);
        }
        System.out.println();
    }

    private static double[] copiarVetor(double[] origem) {
        return origem.clone();
    }
}
