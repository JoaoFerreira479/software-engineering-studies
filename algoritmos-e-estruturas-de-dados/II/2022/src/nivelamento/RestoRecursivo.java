package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class RestoRecursivo {

    private RestoRecursivo() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numerador = lerNumero(scanner, "Digite o numerador: ");
            int denominador = lerNumero(scanner, "Digite o denominador (deve ser maior que zero): ");

            if (denominador <= 0) {
                throw new IllegalArgumentException("O denominador deve ser maior que zero.");
            }

            int resto = calcularResto(Math.abs(numerador), Math.abs(denominador));
            if (numerador < 0) resto = -resto;

            System.out.println("O resto da divisão de " + numerador + " por " + denominador + " é: " + resto);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }

    public static int calcularResto(int numerador, int denominador) {
        if (denominador <= 0) {
            throw new IllegalArgumentException("Denominador deve ser positivo: " + denominador);
        }
        if (numerador < denominador) return numerador;
        return calcularResto(numerador - denominador, denominador);
    }

    public static int lerNumero(Scanner scanner, String mensagem) {
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
