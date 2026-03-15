package aed2.application;

import aed2.domain.recursao.RestoRecursivo;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class RestoRecursivoMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int numerador = entrada.lerInteiro("Digite o numerador: ");
            int denominador = entrada.lerInteiro("Digite o denominador (deve ser maior que zero): ");
            if (denominador <= 0) {
                Console.erro("O denominador deve ser maior que zero.");
                return;
            }
            int resto = RestoRecursivo.calcularResto(Math.abs(numerador), Math.abs(denominador));
            if (numerador < 0) resto = -resto;
            Console.println("O resto da divisão de " + numerador + " por " + denominador + " é: " + resto);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
