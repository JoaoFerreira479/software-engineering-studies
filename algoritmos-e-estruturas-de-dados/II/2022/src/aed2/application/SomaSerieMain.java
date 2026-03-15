package aed2.application;

import aed2.domain.recursao.SomaSerie;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class SomaSerieMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int n = entrada.lerInteiro("Digite um número inteiro e positivo (n): ");
            if (n <= 0) {
                Console.erro("O número deve ser maior que zero.");
                return;
            }
            double soma = SomaSerie.calcularSomaSerie(n);
            Console.printf("Valor final de S: %.4f%n", soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
