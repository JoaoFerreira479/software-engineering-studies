package aed2.application;

import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class PegadinhaEvergreenMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String linha1 = entrada.lerLinha("").trim();
                if (linha1.equals("FIM")) break;
                String linha2 = entrada.lerLinha("").trim();
                Console.println(intercalarStrings(linha1, linha2));
            }
        }
    }

    public static String intercalarStrings(String s1, String s2) {
        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";
        StringBuilder resultado = new StringBuilder();
        int i = 0, j = 0;
        int tam1 = s1.length(), tam2 = s2.length();
        while (i < tam1 || j < tam2) {
            for (int k = 0; k < 2 && i < tam1; k++, i++) {
                resultado.append(s1.charAt(i));
            }
            for (int k = 0; k < 2 && j < tam2; k++, j++) {
                resultado.append(s2.charAt(j));
            }
        }
        return resultado.toString();
    }
}
