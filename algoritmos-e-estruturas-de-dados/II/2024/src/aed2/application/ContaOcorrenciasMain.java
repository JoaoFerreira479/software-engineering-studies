package aed2.application;

import aed2.domain.recursao.ContaOcorrencias;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class ContaOcorrenciasMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String linhaVetor = entrada.lerLinha("").trim();
                if (linhaVetor.equals("FIM")) break;
                int[] vetor = parseVetor(linhaVetor);
                int x = Integer.parseInt(entrada.lerLinha("").trim());
                Console.println(String.valueOf(ContaOcorrencias.contaOcorrencias(vetor, x)));
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
}
