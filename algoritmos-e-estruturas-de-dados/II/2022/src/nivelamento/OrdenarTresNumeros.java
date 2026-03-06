package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class OrdenarTresNumeros {

    private OrdenarTresNumeros() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a = lerNumero(scanner, "Digite o 1º número: ");
            int b = lerNumero(scanner, "Digite o 2º número: ");
            int c = lerNumero(scanner, "Digite o 3º número: ");

            int[] ordenados = ordenarTres(a, b, c);
            System.out.println("Os números em ordem crescente são:");
            System.out.println(formatarVetor(ordenados));
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }

    private static int lerNumero(Scanner scanner, String mensagem) {
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

    public static int[] ordenarTres(int a, int b, int c) {
        int[] v = { a, b, c };
        if (v[0] > v[1]) { int t = v[0]; v[0] = v[1]; v[1] = t; }
        if (v[1] > v[2]) { int t = v[1]; v[1] = v[2]; v[2] = t; }
        if (v[0] > v[1]) { int t = v[0]; v[0] = v[1]; v[1] = t; }
        return v;
    }

    private static String formatarVetor(int[] vetor) {
        if (vetor == null || vetor.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vetor.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(vetor[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
