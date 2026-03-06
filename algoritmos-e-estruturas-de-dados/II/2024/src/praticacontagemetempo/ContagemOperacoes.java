package praticacontagemetempo;

import java.util.Arrays;

public final class ContagemOperacoes {

    private static final long LIMITE_TEMPO_NS = 50_000_000_000L;
    private static final int N_INICIAL = 15625;
    private static final int MEDICOES = 5;
    private static final int CENTRAIS = 3;

    public static void main(String[] args) {
        System.out.println("Resultados do Algoritmo A:");
        System.out.println("N\tOperações\tTempo (ns)");
        int n = N_INICIAL;
        while (n <= 2_000_000_000) {
            long[] tempos = new long[MEDICOES];
            int operacoes = 0;
            for (int i = 0; i < MEDICOES; i++) {
                long inicio = System.nanoTime();
                operacoes = codigoA(n);
                tempos[i] = System.nanoTime() - inicio;
            }
            long tempoMedio = calcularMediaFiltrada(tempos);
            System.out.println(n + "\t" + operacoes + "\t" + tempoMedio);
            if (tempoMedio > LIMITE_TEMPO_NS) break;
            n *= 2;
        }

        System.out.println("\nResultados do Algoritmo B:");
        System.out.println("N\tOperações\tTempo (ns)");
        n = N_INICIAL;
        while (n <= 2_000_000_000) {
            long[] tempos = new long[MEDICOES];
            int[] vetor = new int[n];
            Arrays.fill(vetor, 1);
            int operacoes = 0;
            for (int i = 0; i < MEDICOES; i++) {
                long inicio = System.nanoTime();
                operacoes = codigoB(vetor);
                tempos[i] = System.nanoTime() - inicio;
            }
            long tempoMedio = calcularMediaFiltrada(tempos);
            System.out.println(n + "\t" + operacoes + "\t" + tempoMedio);
            if (tempoMedio > LIMITE_TEMPO_NS) break;
            n *= 2;
        }
    }

    public static int codigoA(int n) {
        int operacoes = 0;
        for (int i = 0; i <= n; i += 2) {
            operacoes++;
        }
        return operacoes;
    }

    public static int codigoB(int[] vet) {
        int operacoes = 0;
        for (int i = 0; i < vet.length; i += 2) {
            for (int j = i; j < (vet.length / 2); j++) {
                vet[i] += vet[j];
                operacoes++;
            }
        }
        return operacoes;
    }

    public static long calcularMediaFiltrada(long[] tempos) {
        if (tempos.length < CENTRAIS + 2) return tempos.length > 0 ? tempos[0] : 0;
        Arrays.sort(tempos);
        long soma = 0;
        for (int i = 1; i <= CENTRAIS; i++) {
            soma += tempos[i];
        }
        return soma / CENTRAIS;
    }
}
