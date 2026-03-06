package laboratorio.algoritmos;

public final class VetorUtil {

    private VetorUtil() {}

    public static int somaPrimeirosN(int[] numeros, int n) {
        validarVetor(numeros);
        if (n <= 0 || n > numeros.length) {
            throw new IllegalArgumentException("Quantidade de termos inválida: " + n + ", tamanho: " + numeros.length);
        }
        int soma = 0;
        for (int i = 0; i < n; i++) {
            soma += numeros[i];
        }
        return soma;
    }

    public static int[] filtrarDivisiveisPor(int[] numeros, int divisor) {
        validarVetor(numeros);
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor não pode ser zero");
        }
        int count = 0;
        for (int num : numeros) {
            if (num % divisor == 0) count++;
        }
        int[] resultado = new int[count];
        int j = 0;
        for (int num : numeros) {
            if (num % divisor == 0) resultado[j++] = num;
        }
        return resultado;
    }

    public static void validarVetor(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("Vetor inválido: nulo ou vazio");
        }
    }

    public static void validarMatriz(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz inválida: nula ou vazia");
        }
    }

    public static String formatar(int[] vetor) {
        if (vetor == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vetor.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(vetor[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
