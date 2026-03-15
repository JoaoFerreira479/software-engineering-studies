package aed1.application;

import aed1.domain.condicional.MediaAproveitamento;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class MediaAproveitamentoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double n1 = entrada.lerDouble("Nota 1: ");
            double n2 = entrada.lerDouble("Nota 2: ");
            double n3 = entrada.lerDouble("Nota 3: ");
            double n4 = entrada.lerDouble("Nota 4: ");
            double media = MediaAproveitamento.media(n1, n2, n3, n4);
            String conceito = MediaAproveitamento.conceito(media);
            String status = MediaAproveitamento.status(conceito);
            Console.printf("Média: %.2f | Conceito: %s | %s%n", media, conceito, status);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
