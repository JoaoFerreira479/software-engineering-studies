package revisao1.app;

import revisao1.domain.RemoverElementoVetor;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class RemoverElementoVetorMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do array (N): ");
            RemoverElementoVetor.validarTamanho(n);
            Console.println("Digite os valores do array:");
            int[] array = LeituraDados.lerVetor(entrada, n);
            interagirRemocao(entrada, array);
            Console.println("Programa encerrado.");
            Console.println("Estado final do array:");
            exibirArray(array);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static void interagirRemocao(final EntradaUsuario entrada, final int[] array) {
        while (true) {
            String resposta = entrada.lerToken("Deseja remover um elemento? (s/n): ").toLowerCase();
            if ("n".equals(resposta)) break;
            if (!"s".equals(resposta)) {
                Console.println("Resposta inválida. Digite 's' ou 'n'.");
                continue;
            }
            int indice = entrada.lerInteiro("Digite o índice do elemento a ser removido: ");
            try {
                RemoverElementoVetor.remover(array, indice);
                Console.println("Elemento removido com sucesso!");
                Console.println("Array atualizado:");
                exibirArray(array);
            } catch (IllegalArgumentException e) {
                Console.println("Índice inválido! Tente novamente.");
            }
        }
    }

    private static void exibirArray(final int[] a) {
        for (int x : a) Console.print(x + " ");
        Console.println("");
    }
}
