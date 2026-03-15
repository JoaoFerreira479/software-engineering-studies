package aed2.application;

import aed2.domain.ordenacao.OrdenacaoPorSelecao;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class OrdenacaoPorSelecaoMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String linha = entrada.lerLinha("").trim();
                if (linha.equals("FIM")) break;

                int[] vetor = parseVetor(linha);
                int comparacoes = OrdenacaoPorSelecao.ordenarPorSelecao(vetor);

                imprimirVetor(vetor);
                Console.println("Comparacoes realizadas: " + comparacoes);
            }
        }
    }

    private static int[] parseVetor(String linha) {
        String[] partes = linha.split(";");
        int[] v = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            v[i] = Integer.parseInt(partes[i].trim());
        }
        return v;
    }

    private static void imprimirVetor(int[] v) {
        for (int i = 0; i < v.length; i++) {
            if (i > 0) Console.print(" ");
            Console.print(String.valueOf(v[i]));
        }
        Console.println("");
    }
}
