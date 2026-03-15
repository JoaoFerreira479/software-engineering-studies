package programarcomputadoresbasicos;

import lab.listas.infrastructure.io.Console;

public final class DevagarSeVaiAoLonge {

    private static final int DISTANCIA_IDA_METROS = 800;
    private static final int DIAS_POR_SEMANA = 5;
    private static final int SEMANAS_POR_ANO = 45;
    private static final double METROS_POR_KM = 1000.0;

    public static void main(final String[] args) {
        final double distanciaTotalKm = calcularDistanciaAnual(DISTANCIA_IDA_METROS, DIAS_POR_SEMANA, SEMANAS_POR_ANO);
        exibirResultado(DISTANCIA_IDA_METROS, DIAS_POR_SEMANA, SEMANAS_POR_ANO, distanciaTotalKm);
    }

    
    public static double calcularDistanciaAnual(final int distanciaIdaMetros, final int diasPorSemana, final int semanasPorAno) {
        final int distanciaPorDia = distanciaIdaMetros * 2;
        final int totalMetros = distanciaPorDia * diasPorSemana * semanasPorAno;
        return totalMetros / METROS_POR_KM;
    }

    private static void exibirResultado(final int distanciaIda, final int diasPorSemana, final int semanasPorAno, final double distanciaTotalKm) {
        Console.println("Parâmetros do Cálculo:");
        Console.printf("- Distância de ida: %d metros%n", distanciaIda);
        Console.printf("- Dias trabalhados por semana: %d%n", diasPorSemana);
        Console.printf("- Semanas trabalhadas por ano: %d%n%n", semanasPorAno);
        Console.printf("Distância total percorrida ao final de um ano: %.2f km%n", distanciaTotalKm);
    }
}
