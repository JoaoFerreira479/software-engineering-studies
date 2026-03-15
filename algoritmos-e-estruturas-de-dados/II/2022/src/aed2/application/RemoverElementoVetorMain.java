package aed2.application;

import aed2.domain.nivelamento.RemoverElementoVetor;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class RemoverElementoVetorMain {

    private static final int INDICE_ENCERRAR = -1;

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

            while (vetor.length > 0) {
                exibirVetor(vetor);
                int indice = entrada.lerInteiro("Digite o índice do elemento a ser removido (-1 para encerrar): ");
                if (indice == INDICE_ENCERRAR) {
                    Console.println("Programa encerrado.");
                    break;
                }
                if (indice < 0 || indice >= vetor.length) {
                    Console.println("Índice inválido! Tente novamente.");
                    continue;
                }
                vetor = RemoverElementoVetor.removerElemento(vetor, indice);
                Console.println("Elemento removido com sucesso.");
            }
            if (vetor.length == 0) {
                Console.println("Todos os elementos foram removidos. Encerrando o programa.");
            }
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static void exibirVetor(int[] vetor) {
        Console.print("Vetor atual: ");
        for (int i = 0; i < vetor.length; i++) {
            Console.print(String.valueOf(vetor[i]) + (i < vetor.length - 1 ? " " : ""));
        }
        Console.println("");
    }
}
