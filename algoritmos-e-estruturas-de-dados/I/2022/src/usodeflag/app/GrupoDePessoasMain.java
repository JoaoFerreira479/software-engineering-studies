package usodeflag.app;

import usodeflag.domain.GrupoDePessoas;
import usodeflag.io.Console;
import usodeflag.io.EntradaUsuario;

import java.util.Scanner;

public final class GrupoDePessoasMain {

    private static final int ENCERRAR = -1;
    private static final int MAX_PESSOAS = 10_000;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Digite a idade e o gênero das pessoas:");
            Console.println("Digite " + ENCERRAR + " na idade para encerrar.");

            int[] idades = new int[MAX_PESSOAS];
            char[] generos = new char[MAX_PESSOAS];
            int n = 0;

            while (n < MAX_PESSOAS) {
                int idade = entrada.lerInteiro("Idade: ");
                if (idade == ENCERRAR) break;

                if (idade < 0) {
                    Console.println("Idade inválida! Digite idade >= 0 ou " + ENCERRAR + " para encerrar.");
                    continue;
                }

                char genero = lerGenero(entrada);
                idades[n] = idade;
                generos[n] = genero;
                n++;
            }

            var res = GrupoDePessoas.calcular(idades, generos, n);
            Console.println("Resultados:");
            Console.println("Número de homens: " + res.quantidadeHomens());
            Console.println("Número de mulheres: " + res.quantidadeMulheres());
            Console.printf("Média de idades dos homens: %.2f%n", res.mediaIdadeHomens());
            if (res.temDados()) {
                Console.println("Maior idade verificada: " + res.maiorIdade());
                Console.println("Menor idade verificada: " + res.menorIdade());
            } else {
                Console.println("Nenhum dado foi inserido.");
            }
        } catch (Exception e) {
            Console.erro("Erro inesperado: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static char lerGenero(final EntradaUsuario entrada) {
        while (true) {
            String token = entrada.lerToken("Gênero (M para masculino, F para feminino): ").toUpperCase();
            if (token.isEmpty()) continue;
            char c = token.charAt(0);
            if (c == 'M' || c == 'F') return c;
            Console.println("Gênero inválido! Digite M (masculino) ou F (feminino).");
        }
    }
}
