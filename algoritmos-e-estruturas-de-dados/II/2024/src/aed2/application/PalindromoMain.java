package aed2.application;

import aed2.domain.recursao.Palindromo;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class PalindromoMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String entradaStr = entrada.lerLinha("").trim();
                if (entradaStr.equals("FIM")) break;
                Console.println(Palindromo.ehPalindromo(entradaStr) ? "SIM" : "NAO");
            }
        }
    }
}
