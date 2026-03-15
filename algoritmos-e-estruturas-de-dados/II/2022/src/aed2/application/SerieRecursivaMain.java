package aed2.application;

import aed2.domain.recursao.SerieRecursiva;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class SerieRecursivaMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int n = entrada.lerInteiro("Digite o valor de N (inteiro positivo): ");
            if (n <= 0) {
                Console.erro("O valor deve ser maior que zero.");
                return;
            }
            double resultado = SerieRecursiva.calcularSerie(n);
            Console.printf("O valor da série S para N = %d é: %.4f%n", n, resultado);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
