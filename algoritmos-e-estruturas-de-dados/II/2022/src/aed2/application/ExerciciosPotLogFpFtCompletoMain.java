package aed2.application;

import aed2.infrastructure.io.Console;

public final class ExerciciosPotLogFpFtCompletoMain {

    private ExerciciosPotLogFpFtCompletoMain() {}

    public static void main(String[] args) {
        Console.println("Exercícios - potências, logaritmos, função piso e função teto\n");
        exibirPotencias();
        exibirLogaritmos();
        exibirPisoETeto();
    }

    private static void exibirPotencias() {
        Console.println("Potências:");
        double[][] valores = {
            { 2, 3 }, { 2, 10 }, { 2, 20 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 2, 5 }
        };
        for (int i = 0; i < valores.length; i++) {
            double base = valores[i][0];
            double expoente = valores[i][1];
            double resultado = Math.pow(base, expoente);
            Console.printf("%d) %.0f^%.0f = %.0f%n", i + 1, base, expoente, resultado);
        }
        Console.println("");
    }

    private static void exibirLogaritmos() {
        Console.println("Logaritmos:");
        double[] valores = { 256, 1024, 60, 2024, 2048, 32, 15 };
        for (int i = 0; i < valores.length; i++) {
            double x = valores[i];
            double log = Math.log10(x);
            Console.printf("%d) log(%.0f) = %.5f%n", i + 8, x, log);
        }
        Console.println("");
    }

    private static void exibirPisoETeto() {
        Console.println("Funções Piso e Teto:");
        double[] valoresLog = { 200, 60 };
        for (int i = 0; i < valoresLog.length; i++) {
            double x = valoresLog[i];
            double log = Math.log10(x);
            Console.printf("%d) log(%.0f): ⌊log(x)⌋ = %.0f, ⌈log(x)⌉ = %.0f%n",
                i + 15, x, Math.floor(log), Math.ceil(log));
        }
        double[] valores = { 2.34, 18.2 };
        for (int i = 0; i < valores.length; i++) {
            double x = valores[i];
            Console.printf("%d) %.2f: ⌊x⌋ = %.0f, ⌈x⌉ = %.0f%n",
                i + 18, x, Math.floor(x), Math.ceil(x));
        }
        Console.println("");
    }
}
