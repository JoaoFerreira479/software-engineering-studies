package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class RemoverElementoVetor {

    private static final int INDICE_ENCERRAR = -1;

    private RemoverElementoVetor() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = lerInteiro(scanner, "Digite o tamanho do vetor (N): ");
            if (n <= 0) {
                throw new IllegalArgumentException("O tamanho do vetor deve ser maior que zero.");
            }

            int[] vetor = new int[n];
            System.out.println("Digite os elementos do vetor:");
            for (int i = 0; i < n; i++) {
                vetor[i] = lerInteiro(scanner, "Elemento [" + i + "]: ");
            }

            while (vetor.length > 0) {
                exibirVetor(vetor);
                int indice = lerInteiro(scanner, "Digite o índice do elemento a ser removido (-1 para encerrar): ");

                if (indice == INDICE_ENCERRAR) {
                    System.out.println("Programa encerrado.");
                    break;
                }

                if (indice < 0 || indice >= vetor.length) {
                    System.out.println("Índice inválido! Tente novamente.");
                    continue;
                }

                vetor = removerElemento(vetor, indice);
                System.out.println("Elemento removido com sucesso.");
            }

            if (vetor.length == 0) {
                System.out.println("Todos os elementos foram removidos. Encerrando o programa.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }

    private static void exibirVetor(int[] vetor) {
        System.out.print("Vetor atual: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + (i < vetor.length - 1 ? " " : ""));
        }
        System.out.println();
    }

    public static int[] removerElemento(int[] vetor, int indice) {
        if (vetor == null || indice < 0 || indice >= vetor.length) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        int[] novo = new int[vetor.length - 1];
        for (int i = 0, j = 0; i < vetor.length; i++) {
            if (i != indice) novo[j++] = vetor[i];
        }
        return novo;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }
}
