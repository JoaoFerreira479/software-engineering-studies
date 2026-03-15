package aed2.application;

import aed2.domain.recursao.AlgebraBooleana;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class AlgebraBooleanaMain {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            while (true) {
                String linha = entrada.lerLinha("").trim();
                if (linha.equals("FIM")) break;

                String[] partes = linha.split(" ");
                int n = Integer.parseInt(partes[0]);
                int[] valores = new int[n];
                for (int i = 0; i < n; i++) {
                    valores[i] = Integer.parseInt(partes[i + 1]);
                }
                int idxExpr = n + 1;
                if (idxExpr >= partes.length) continue;
                String expressao = linha.substring(linha.indexOf(partes[idxExpr]));

                boolean resultado = AlgebraBooleana.avaliarExpressao(expressao.trim(), valores);
                Console.println(resultado ? "1" : "0");
            }
        }
    }
}
