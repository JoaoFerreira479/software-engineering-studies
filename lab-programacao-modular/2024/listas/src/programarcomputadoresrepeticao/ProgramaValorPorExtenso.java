package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaValorPorExtenso {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                final double valor = EntradaUtil.lerDouble(scanner, "Digite um valor monetário (0 para sair): R$ ");
                if (valor == 0) {
                    Console.println("Programa encerrado.");
                    break;
                }
                if (valor < 0 || valor > 999_999.99) {
                    Console.println("Erro: O valor deve estar entre 0 e 999.999,99.");
                    continue;
                }
                Console.println("Valor por extenso: " + ConversorValorPorExtenso.converterValorMonetario(valor));
            }
        }
    }
}
