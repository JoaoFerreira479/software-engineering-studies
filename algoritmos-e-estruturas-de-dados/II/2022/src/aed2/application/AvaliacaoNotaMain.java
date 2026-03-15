package aed2.application;

import aed2.domain.nivelamento.AvaliacaoNota;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class AvaliacaoNotaMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            double nota = lerNota(entrada);
            String classificacao = AvaliacaoNota.classificarNota(nota);
            Console.printf("A classificação da nota %.1f é: %s%n", nota, classificacao);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static double lerNota(EntradaUsuario entrada) {
        while (true) {
            double nota = entrada.lerDouble("Digite a nota (entre 0 e 10): ");
            if (nota >= 0 && nota <= 10) return nota;
            Console.erro("Nota inválida! Digite uma nota entre 0 e 10.");
        }
    }
}
