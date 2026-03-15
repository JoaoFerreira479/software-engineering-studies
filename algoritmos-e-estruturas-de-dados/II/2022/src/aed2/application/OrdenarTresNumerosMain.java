package aed2.application;

import aed2.domain.nivelamento.OrdenarTresNumeros;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class OrdenarTresNumerosMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int a = entrada.lerInteiro("Digite o 1º número: ");
            int b = entrada.lerInteiro("Digite o 2º número: ");
            int c = entrada.lerInteiro("Digite o 3º número: ");
            int[] ordenados = OrdenarTresNumeros.ordenarTres(a, b, c);
            Console.println("Os números em ordem crescente são:");
            Console.println("[" + ordenados[0] + ", " + ordenados[1] + ", " + ordenados[2] + "]");
        }
    }
}
