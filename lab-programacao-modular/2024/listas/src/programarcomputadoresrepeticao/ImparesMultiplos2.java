package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ImparesMultiplos2 {

    public static List<Integer> obterImparesMultiplosDeTres(final int inicio, final int fim) {
        final List<Integer> numeros = new ArrayList<>();
        for (int i = fim; i >= inicio; i--) {
            if (i % 2 != 0 && i % 3 == 0) {
                numeros.add(i);
            }
        }
        return numeros;
    }

    public static void exibirNumeros(final List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            Console.println("Nenhum número ímpar múltiplo de três foi encontrado no intervalo informado.");
        } else {
            Console.println("Números ímpares múltiplos de três no intervalo fornecido (ordem decrescente):");
            Console.println(numeros);
        }
    }

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int inicio = EntradaUtil.lerInteiro(scanner, "Digite o valor inicial do intervalo: ");
            final int fim = EntradaUtil.lerInteiro(scanner, "Digite o valor final do intervalo: ");
            if (inicio > fim) {
                Console.println("Erro: O valor inicial não pode ser maior que o valor final.");
            } else {
                exibirNumeros(obterImparesMultiplosDeTres(inicio, fim));
            }
        } catch (NumberFormatException e) {
            Console.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        }
    }
}
