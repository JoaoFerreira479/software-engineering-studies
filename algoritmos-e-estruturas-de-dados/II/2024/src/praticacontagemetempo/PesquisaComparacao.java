package praticacontagemetempo;

import java.util.Arrays;
import java.util.Random;

public final class PesquisaComparacao {

    private static final long LIMITE_N = 2_000_000_000L;
    private static final int N_INICIAL = 7_500_000;
    private static final int MEDICOES = 5;
    private static final int CENTRAIS = 3;

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("Pesquisa Sequencial:");
        System.out.println("N\tCenário\tComparações\tTempo (ns)");
        int n = N_INICIAL;
        while (n <= LIMITE_N) {
            int[] vetor = gerarVetorOrdenado(n);
            int qualquer = vetor[rand.nextInt(n)];
            int primeiro = vetor[0];
            int inexistente = -1;
            executarPesquisa("Sequencial", n, vetor, qualquer, primeiro, inexistente);
            n *= 2;
        }

        System.out.println("\nPesquisa Binária:");
        System.out.println("N\tCenário\tComparações\tTempo (ns)");
        n = N_INICIAL;
        while (n <= LIMITE_N) {
            int[] vetor = gerarVetorOrdenado(n);
            int qualquer = vetor[rand.nextInt(n)];
            int primeiro = vetor[0];
            int inexistente = -1;
            executarPesquisa("Binária", n, vetor, qualquer, primeiro, inexistente);
            n *= 2;
        }
    }

    public static void executarPesquisa(String tipo, int n, int[] vetor, int qualquer, int primeiro, int inexistente) {
        realizarTeste(tipo, "Qualquer", vetor, qualquer);
        realizarTeste(tipo, "Primeiro", vetor, primeiro);
        realizarTeste(tipo, "Inexistente", vetor, inexistente);
    }

    public static void realizarTeste(String tipo, String caso, int[] vetor, int chave) {
        long[] tempos = new long[MEDICOES];
        int comparacoes = 0;
        for (int i = 0; i < MEDICOES; i++) {
            long inicio = System.nanoTime();
            comparacoes = "Sequencial".equals(tipo)
                ? pesquisaSequencial(vetor, chave)
                : pesquisaBinaria(vetor, chave);
            tempos[i] = System.nanoTime() - inicio;
        }
        long tempoMedio = calcularMediaFiltrada(tempos);
        System.out.println(vetor.length + "\t" + caso + "\t" + comparacoes + "\t" + tempoMedio);
    }

    public static int pesquisaSequencial(int[] vetor, int chave) {
        int comparacoes = 0;
        for (int i = 0; i < vetor.length; i++) {
            comparacoes++;
            if (vetor[i] == chave) return comparacoes;
        }
        return comparacoes;
    }

    public static int pesquisaBinaria(int[] vetor, int chave) {
        int inicio = 0;
        int fim = vetor.length - 1;
        int comparacoes = 0;
        while (inicio <= fim) {
            comparacoes++;
            int meio = (inicio + fim) / 2;
            if (vetor[meio] == chave) return comparacoes;
            if (vetor[meio] < chave) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return comparacoes;
    }

    public static int[] gerarVetorOrdenado(int n) {
        int[] vetor = new int[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = i * 2;
        }
        return vetor;
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
