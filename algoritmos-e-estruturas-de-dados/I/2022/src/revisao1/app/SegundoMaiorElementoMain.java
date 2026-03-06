package revisao1.app;

import revisao1.domain.SegundoMaiorElemento;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class SegundoMaiorElementoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do vetor (N): ");
            SegundoMaiorElemento.validarTamanho(n);
            Console.println("Digite os valores do vetor:");
            int[] vetor = LeituraDados.lerVetor(entrada, n);
            int segundoMaior = SegundoMaiorElemento.segundoMaior(vetor);
            Console.printf("O segundo maior elemento do vetor é: %d%n", segundoMaior);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
