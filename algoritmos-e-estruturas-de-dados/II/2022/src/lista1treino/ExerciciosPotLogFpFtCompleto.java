package lista1treino;

public final class ExerciciosPotLogFpFtCompleto {

    private ExerciciosPotLogFpFtCompleto() {}

    public static void main(String[] args) {
        System.out.println("Exercícios - potências, logaritmos, função piso e função teto\n");
        exibirPotencias();
        exibirLogaritmos();
        exibirPisoETeto();
    }

    private static void exibirPotencias() {
        System.out.println("Potências:");
        double[][] valores = {
            { 2, 3 }, { 2, 10 }, { 2, 20 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 2, 5 }
        };
        for (int i = 0; i < valores.length; i++) {
            double base = valores[i][0];
            double expoente = valores[i][1];
            double resultado = potencia(base, expoente);
            System.out.printf("%d) %.0f^%.0f = %.0f%n", i + 1, base, expoente, resultado);
        }
        System.out.println();
    }

    private static double potencia(double base, double expoente) {
        if (!Double.isFinite(base) || !Double.isFinite(expoente)) {
            throw new IllegalArgumentException("Base e expoente devem ser finitos");
        }
        return Math.pow(base, expoente);
    }

    private static void exibirLogaritmos() {
        System.out.println("Logaritmos:");
        double[] valores = { 256, 1024, 60, 2024, 2048, 32, 15 };
        for (int i = 0; i < valores.length; i++) {
            double x = valores[i];
            double log = log10Seguro(x);
            System.out.printf("%d) log(%.0f) = %.5f%n", i + 8, x, log);
        }
        System.out.println();
    }

    private static double log10Seguro(double x) {
        if (x <= 0 || !Double.isFinite(x)) {
            throw new IllegalArgumentException("Logaritmo exige valor positivo e finito: " + x);
        }
        return Math.log10(x);
    }

    private static void exibirPisoETeto() {
        System.out.println("Funções Piso e Teto:");

        double[] valoresLog = { 200, 60 };
        for (int i = 0; i < valoresLog.length; i++) {
            double x = valoresLog[i];
            double log = log10Seguro(x);
            System.out.printf("%d) log(%.0f): ⌊log(x)⌋ = %.0f, ⌈log(x)⌉ = %.0f%n",
                i + 15, x, Math.floor(log), Math.ceil(log));
        }

        double[] valores = { 2.34, 18.2 };
        for (int i = 0; i < valores.length; i++) {
            double x = valores[i];
            System.out.printf("%d) %.2f: ⌊x⌋ = %.0f, ⌈x⌉ = %.0f%n",
                i + 18, x, Math.floor(x), Math.ceil(x));
        }
        System.out.println();
    }
}
