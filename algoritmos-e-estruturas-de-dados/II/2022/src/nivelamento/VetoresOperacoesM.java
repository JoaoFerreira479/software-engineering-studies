package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class VetoresOperacoesM {

    private VetoresOperacoesM() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int tamanho = lerTamanhoVetor(scanner, "Digite o tamanho dos vetores: ");
            int[] x = new int[tamanho];
            int[] y = new int[tamanho];

            System.out.println("Preenchendo o vetor X:");
            preencherVetor(scanner, x);
            System.out.println("Preenchendo o vetor Y:");
            preencherVetor(scanner, y);

            System.out.println("\nCalculando a soma dos vetores X e Y:");
            calcularESomarVetores(x, y);
            System.out.println("\nCalculando o produto dos vetores X e Y:");
            calcularEProdutoVetores(x, y);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }

    private static int lerTamanhoVetor(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int tamanho = scanner.nextInt();
                if (tamanho <= 0) {
                    throw new IllegalArgumentException("O tamanho do vetor deve ser maior que zero.");
                }
                return tamanho;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }

    public static void preencherVetor(Scanner scanner, int[] vetor) {
        if (vetor == null) return;
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            try {
                vetor[i] = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número inteiro.");
                scanner.nextLine();
                i--;
            }
        }
    }

    public static void calcularESomarVetores(int[] x, int[] y) {
        if (x == null || y == null || x.length != y.length) {
            throw new IllegalArgumentException("Vetores devem ter o mesmo tamanho.");
        }
        int[] soma = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            soma[i] = x[i] + y[i];
        }
        System.out.println("Vetor Soma: " + vetorParaString(soma));
    }

    public static void calcularEProdutoVetores(int[] x, int[] y) {
        if (x == null || y == null || x.length != y.length) {
            throw new IllegalArgumentException("Vetores devem ter o mesmo tamanho.");
        }
        int[] produto = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            produto[i] = x[i] * y[i];
        }
        System.out.println("Vetor Produto: " + vetorParaString(produto));
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
