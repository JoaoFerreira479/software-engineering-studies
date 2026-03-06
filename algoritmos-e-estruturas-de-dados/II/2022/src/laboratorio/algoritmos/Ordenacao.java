package laboratorio.algoritmos;

public final class Ordenacao {

    private Ordenacao() {}

    public static void quickSort(double[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(vetor, inicio, fim);
            quickSort(vetor, inicio, pivoIndex - 1);
            quickSort(vetor, pivoIndex + 1, fim);
        }
    }

    private static int particionar(double[] vetor, int inicio, int fim) {
        double pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                trocar(vetor, i, j);
            }
        }
        trocar(vetor, i + 1, fim);
        return i + 1;
    }

    public static void heapSort(double[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        int n = vetor.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            trocar(vetor, 0, i);
            heapify(vetor, i, 0);
        }
    }

    private static void heapify(double[] vetor, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        if (esq < n && vetor[esq] > vetor[maior]) maior = esq;
        if (dir < n && vetor[dir] > vetor[maior]) maior = dir;
        if (maior != i) {
            trocar(vetor, i, maior);
            heapify(vetor, n, maior);
        }
    }

    private static void trocar(double[] vetor, int i, int j) {
        double temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}
