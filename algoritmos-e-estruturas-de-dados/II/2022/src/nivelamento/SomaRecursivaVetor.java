package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class SomaRecursivaVetor {

    private SomaRecursivaVetor() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = lerInteiroPositivo(scanner, "Digite o tamanho do vetor (N): ");
            int[] vetor = new int[n];
            System.out.println("Digite os elementos do vetor:");
            preencherVetor(scanner, vetor);

            int soma = calcularSoma(vetor, vetor.length - 1);
            System.out.println("A soma dos elementos do vetor é: " + soma);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }

    public static int calcularSoma(int[] vetor, int indice) {
        if (vetor == null) return 0;
        if (indice < 0) return 0;
        return vetor[indice] + calcularSoma(vetor, indice - 1);
    }

    private static void preencherVetor(Scanner scanner, int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = lerInteiro(scanner, String.format("Elemento %d: ", i + 1));
        }
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            int v = lerInteiro(scanner, mensagem);
            if (v > 0) return v;
            System.out.println("Erro: O tamanho deve ser maior que zero.");
        }
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
