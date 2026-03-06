package revisao1.app;

import revisao1.domain.ContarDuplicados;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class ContarDuplicadosMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do vetor: ");
            ContarDuplicados.validarTamanho(n);
            Console.println("Digite os valores do vetor:");
            int[] vetor = LeituraDados.lerVetor(entrada, n);
            int numDuplicados = ContarDuplicados.contar(vetor);
            Console.printf("O número de elementos duplicados no vetor é: %d%n", numDuplicados);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
