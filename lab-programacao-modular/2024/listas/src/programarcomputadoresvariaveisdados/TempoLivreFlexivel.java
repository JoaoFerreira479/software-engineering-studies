package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public class TempoLivreFlexivel {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int tempoTotalMinutos = EntradaUtil.lerInteiro(scanner,
                    "Digite o tempo total disponível para estudo (em minutos): ");
            int numeroDisciplinas = EntradaUtil.lerInteiro(scanner, "Digite o número de disciplinas: ");

            try {
                TempoDeEstudo estudo = new TempoDeEstudo(tempoTotalMinutos, numeroDisciplinas);

                int tempoPorDisciplina = estudo.calcularTempoPorDisciplina();
                int tempoLivre = estudo.calcularTempoLivre();

                exibirResultados(tempoPorDisciplina, tempoLivre);
            } catch (IllegalArgumentException e) {
                Console.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void exibirResultados(int tempoPorDisciplina, int tempoLivre) {
        int[] tempoDisciplinaFormatado = TempoDeEstudo.converterParaHorasEMinutos(tempoPorDisciplina);
        int[] tempoLivreFormatado = TempoDeEstudo.converterParaHorasEMinutos(tempoLivre);

        Console.println("\n--- Tempo de Estudo ---");
        Console.printf("Tempo por disciplina: %d horas e %d minutos\n", tempoDisciplinaFormatado[0],
                tempoDisciplinaFormatado[1]);
        Console.printf("Tempo livre: %d horas e %d minutos\n", tempoLivreFormatado[0], tempoLivreFormatado[1]);
    }
}
