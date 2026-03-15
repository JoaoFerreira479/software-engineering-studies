package aed1.application;

import aed1.domain.condicional.OrdenarNumeros;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class OrdenarNumerosMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int a = entrada.lerInteiro("Primeiro número: ");
            int b = entrada.lerInteiro("Segundo número: ");
            int c = entrada.lerInteiro("Terceiro número: ");
            int[] ordenados = OrdenarNumeros.ordenarTres(a, b, c);
            Console.printf("Ordenados: %d %d %d%n", ordenados[0], ordenados[1], ordenados[2]);
        } finally {
            entrada.fechar();
        }
    }
}
