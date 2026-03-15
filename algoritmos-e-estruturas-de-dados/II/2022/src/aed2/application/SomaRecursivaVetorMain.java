package aed2.application;

import aed2.domain.recursao.SomaRecursivaVetor;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class SomaRecursivaVetorMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int n = entrada.lerInteiro("Digite o tamanho do vetor (N): ");
            if (n <= 0) {
                Console.erro("O tamanho deve ser maior que zero.");
                return;
            }
            Console.println("Digite os elementos do vetor:");
            int[] vetor = LeituraDados.lerVetorInteiros(entrada, n);
            int soma = SomaRecursivaVetor.calcularSoma(vetor, vetor.length - 1);
            Console.println("A soma dos elementos do vetor é: " + soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
