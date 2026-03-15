package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaSalario {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double valorNominal = EntradaUtil.lerDoublePositivo(scanner, "Digite o valor nominal do salário: R$ ");
            final int horasExtras = EntradaUtil.lerInteiro(scanner, "Digite o número de horas extras: ");
            final Salario salario = new Salario(valorNominal, horasExtras);
            Console.println("\n=== Detalhamento do Salário ===");
            Console.println(salario);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
