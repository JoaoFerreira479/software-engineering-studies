package aed1.application;

import aed1.domain.vetores.ContarDuplicados;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class ContarDuplicadosMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do vetor: ");
            ContarDuplicados.validarTamanho(n);
            Console.println("Digite os valores do vetor:");
            int[] vetor = LeituraDados.lerVetor(entrada, n);
            int qtd = ContarDuplicados.contar(vetor);
            Console.println("Quantidade de valores que aparecem mais de uma vez: " + qtd);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
