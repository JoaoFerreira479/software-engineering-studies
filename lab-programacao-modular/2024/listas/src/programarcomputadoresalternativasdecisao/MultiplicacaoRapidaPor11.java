package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class MultiplicacaoRapidaPor11 {

    private static final int MIN_DOIS_DIGITOS = 10;
    private static final int MAX_DOIS_DIGITOS = 99;

    public static String multiplicarPor11(final int numero) {
        if (numero < MIN_DOIS_DIGITOS || numero > MAX_DOIS_DIGITOS) {
            throw new IllegalArgumentException("O número deve ter exatamente dois dígitos entre 10 e 99.");
        }
        final int unidade = numero % 10;
        final int dezena = numero / 10;
        int soma = dezena + unidade;
        int d = dezena;
        if (soma >= 10) {
            d += soma / 10;
            soma %= 10;
        }
        return "" + d + soma + unidade;
    }

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int numero = EntradaUtil.lerInteiro(scanner, "Digite um número de dois dígitos para multiplicar por 11: ");
            final String resultado = multiplicarPor11(numero);
            System.out.println("O resultado da multiplicação por 11 é: " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
