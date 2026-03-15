package aed2.application;

import aed2.domain.recursao.IndicesParesRecursivo;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class IndicesParesRecursivoMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int n = entrada.lerInteiro("Digite o tamanho do vetor (N): ");
            if (n <= 0) {
                Console.erro("O tamanho do vetor deve ser maior que zero.");
                return;
            }
            Console.println("Digite os elementos do vetor:");
            int[] vetor = LeituraDados.lerVetorInteiros(entrada, n);
            Console.println("Vetor completo:");
            exibirVetor(vetor);
            Console.println("Valores nos índices pares do vetor:");
            int[] pares = IndicesParesRecursivo.valoresNosIndicesPares(vetor);
            for (int i = 0; i < pares.length; i++) {
                Console.printf("Índice %d: %d%n", i * 2, pares[i]);
            }
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static void exibirVetor(int[] vetor) {
        if (vetor == null) return;
        for (int i = 0; i < vetor.length; i++) {
            Console.print(vetor[i] + (i < vetor.length - 1 ? " " : ""));
        }
        Console.println("");
    }
}
