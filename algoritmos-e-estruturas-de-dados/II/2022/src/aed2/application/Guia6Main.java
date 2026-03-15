package aed2.application;

import aed2.domain.algoritmos.Ordenacao;
import aed2.infrastructure.io.Console;

import java.util.Random;

public final class Guia6Main {

    private Guia6Main() {}

    public static void main(String[] args) {
        int tamanho = 1_000_000;
        if (tamanho <= 0) {
            Console.erro("Tamanho inválido.");
            return;
        }
        Console.println("Gerando vetor aleatório...");
        long inicio = System.currentTimeMillis();
        double[] vetor = geraAleatorio(tamanho);
        long fim = System.currentTimeMillis();
        Console.printf("Tempo para gerar vetor: %.5f segundos%n", (fim - inicio) / 1000.0);
        exibirAmostra(vetor, "Vetor gerado", 10);

        Console.println("\nOrdenando com QuickSort...");
        double[] copiaQuickSort = vetor.clone();
        inicio = System.currentTimeMillis();
        Ordenacao.quickSort(copiaQuickSort, 0, copiaQuickSort.length - 1);
        fim = System.currentTimeMillis();
        Console.printf("Tempo para QuickSort: %.5f segundos%n", (fim - inicio) / 1000.0);
        exibirAmostra(copiaQuickSort, "Vetor ordenado com QuickSort", 10);

        Console.println("\nOrdenando com HeapSort...");
        double[] copiaHeapSort = vetor.clone();
        inicio = System.currentTimeMillis();
        Ordenacao.heapSort(copiaHeapSort);
        fim = System.currentTimeMillis();
        Console.printf("Tempo para HeapSort: %.5f segundos%n", (fim - inicio) / 1000.0);
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

    private static void exibirAmostra(double[] vetor, String descricao, int quantidade) {
        if (vetor == null) return;
        int n = Math.min(quantidade, vetor.length);
        Console.printf("%n%s (primeiros %d elementos):%n", descricao, n);
        for (int i = 0; i < n; i++) {
            Console.printf("%.5f ", vetor[i]);
        }
        Console.println("");
    }
}
