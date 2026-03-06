package programarcomputadoresrepeticao;

public final class ImparesMultiplos1 {

    public static void main(final String[] args) {
        final int inicio = 1;
        final int fim = 1000;
        final int resultado = calcularSomaImparesMultiplosDeTres(inicio, fim);
        System.out.println("A soma de todos os números ímpares múltiplos de três na faixa de " + inicio + " a " + fim + " é: " + resultado);
    }

    public static int calcularSomaImparesMultiplosDeTres(final int inicio, final int fim) {
        int soma = 0;
        for (int i = inicio; i <= fim; i++) {
            if (i % 2 != 0 && i % 3 == 0) {
                soma += i;
            }
        }
        return soma;
    }
}
