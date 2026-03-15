package aed2.application;

import aed2.domain.bemformada.BemFormada;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class BemFormadaMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String expressao = entrada.lerLinha("").trim();
                if (expressao.equals("FIM")) break;
                Console.println(BemFormada.verificarBemFormada(expressao) ? "correto" : "incorreto");
            }
        }
    }
}
