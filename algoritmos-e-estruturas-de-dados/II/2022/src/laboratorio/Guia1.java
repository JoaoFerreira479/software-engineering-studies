package laboratorio;

import laboratorio.algoritmos.Fibonacci;
import laboratorio.algoritmos.VetorUtil;

public final class Guia1 {

    private Guia1() {}

    public static void main(String[] args) {
        try {
            executarExemploFibonacci();
            executarExemploSomaDosTermos();
            executarExemploDivisiveisPor();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void executarExemploFibonacci() {
        int quantidade = 10;
        int[] fibonacci = Fibonacci.gerar(quantidade);
        System.out.println("Série de Fibonacci: " + VetorUtil.formatar(fibonacci));
    }

    private static void executarExemploSomaDosTermos() {
        int[] vetorExemplo = { 1, 3, 5, 7, 9, 11 };
        int soma = VetorUtil.somaPrimeirosN(vetorExemplo, 4);
        System.out.println("Soma dos primeiros 4 números: " + soma);
    }

    private static void executarExemploDivisiveisPor() {
        int[] vetorDivisiveis = { 10, 15, 22, 33, 40 };
        int divisor = 10;
        int[] divisiveisPor10 = VetorUtil.filtrarDivisiveisPor(vetorDivisiveis, divisor);
        System.out.println("Números divisíveis por " + divisor + ": " + VetorUtil.formatar(divisiveisPor10));
    }
}
