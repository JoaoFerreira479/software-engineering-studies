package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaImposto {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double valorBase = EntradaUtil.lerDoubleNaoNegativo(scanner, "Digite o valor base: R$ ");
            final CalculadoraImposto calculadora = new CalculadoraImposto();
            final double imposto = calculadora.calcularImposto(valorBase);
            Console.printf("Valor Base: R$ %.2f%n", valorBase);
            Console.printf("Imposto a Pagar: R$ %.2f%n", imposto);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
