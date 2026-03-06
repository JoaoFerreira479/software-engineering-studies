package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaImposto {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double valorBase = EntradaUtil.lerDoubleNaoNegativo(scanner, "Digite o valor base: R$ ");
            final CalculadoraImposto calculadora = new CalculadoraImposto();
            final double imposto = calculadora.calcularImposto(valorBase);
            System.out.printf("Valor Base: R$ %.2f%n", valorBase);
            System.out.printf("Imposto a Pagar: R$ %.2f%n", imposto);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
