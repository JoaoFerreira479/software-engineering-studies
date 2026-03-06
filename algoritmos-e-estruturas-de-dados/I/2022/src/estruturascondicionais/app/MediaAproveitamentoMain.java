package estruturascondicionais.app;

import estruturascondicionais.domain.MediaAproveitamento;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class MediaAproveitamentoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double n1 = entrada.lerDouble("Digite a nota da prova 1 (0 a 100): ");
            double n2 = entrada.lerDouble("Digite a nota da prova 2 (0 a 100): ");
            double n3 = entrada.lerDouble("Digite a nota da prova 3 (0 a 100): ");
            double n4 = entrada.lerDouble("Digite a nota da prova 4 (0 a 100): ");
            double media = MediaAproveitamento.media(n1, n2, n3, n4);
            String conceito = MediaAproveitamento.conceito(media);
            String status = MediaAproveitamento.status(conceito);
            Console.printf("Média de Aproveitamento (MA): %.2f%n", media);
            Console.printf("Conceito: %s%n", conceito);
            Console.printf("Status: %s%n", status);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
