package aed2.application;

import aed2.domain.nivelamento.VetoresOperacoesM;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class VetoresOperacoesMMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int tamanho = entrada.lerInteiro("Digite o tamanho dos vetores: ");
            if (tamanho <= 0) {
                Console.erro("O tamanho do vetor deve ser maior que zero.");
                return;
            }
            int[] x = new int[tamanho];
            int[] y = new int[tamanho];
            Console.println("Preenchendo o vetor X:");
            for (int i = 0; i < tamanho; i++) {
                x[i] = entrada.lerInteiro("Digite o elemento " + (i + 1) + ": ");
            }
            Console.println("Preenchendo o vetor Y:");
            for (int i = 0; i < tamanho; i++) {
                y[i] = entrada.lerInteiro("Digite o elemento " + (i + 1) + ": ");
            }
            Console.println("\nCalculando a soma dos vetores X e Y:");
            int[] soma = VetoresOperacoesM.somarVetores(x, y);
            Console.println("Vetor Soma: " + VetoresOperacoesM.vetorParaString(soma));
            Console.println("\nCalculando o produto dos vetores X e Y:");
            int[] produto = VetoresOperacoesM.produtoVetores(x, y);
            Console.println("Vetor Produto: " + VetoresOperacoesM.vetorParaString(produto));
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
