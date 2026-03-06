package laboratorio.algoritmos;

public final class Fibonacci {

    private Fibonacci() {}

    public static int[] gerar(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0: " + quantidade);
        }
        int[] serie = new int[quantidade];
        if (quantidade >= 1) serie[0] = 0;
        if (quantidade >= 2) serie[1] = 1;
        for (int i = 2; i < quantidade; i++) {
            serie[i] = serie[i - 1] + serie[i - 2];
        }
        return serie;
    }
}
