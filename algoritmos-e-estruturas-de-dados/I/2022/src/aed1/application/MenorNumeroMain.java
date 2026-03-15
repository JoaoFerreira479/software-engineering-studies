package aed1.application;

import aed1.domain.condicional.MenorNumero;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class MenorNumeroMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int a = entrada.lerInteiro("Primeiro número: ");
            int b = entrada.lerInteiro("Segundo número: ");
            int c = entrada.lerInteiro("Terceiro número: ");
            Console.println("Menor: " + MenorNumero.menor(a, b, c));
        } finally {
            entrada.fechar();
        }
    }
}
