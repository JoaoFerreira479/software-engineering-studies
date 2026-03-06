package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class MaiorDeTres {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int numero1 = EntradaUtil.lerInteiro(scanner, "Digite o primeiro número: ");
            final int numero2 = EntradaUtil.lerInteiro(scanner, "Digite o segundo número: ");
            final int numero3 = EntradaUtil.lerInteiro(scanner, "Digite o terceiro número: ");

            final Numeros numeros = new Numeros(numero1, numero2, numero3);
            System.out.println("O maior número é: " + numeros.maior());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
