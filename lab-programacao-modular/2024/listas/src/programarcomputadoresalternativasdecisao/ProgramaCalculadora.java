package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaCalculadora {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int num1 = EntradaUtil.lerInteiro(scanner, "Digite o primeiro número: ");
            final int num2 = EntradaUtil.lerInteiro(scanner, "Digite o segundo número: ");
            final String opStr = EntradaUtil.lerLinha(scanner, "Digite o operador (+, -, *, /): ");
            if (opStr.isEmpty()) {
                Console.println("Erro: Operador não pode ser vazio.");
                return;
            }
            final Operador operador = Operador.fromChar(opStr.charAt(0));
            final double resultado = Calculadora.calcular(num1, num2, operador);
            Console.println("Resultado: " + resultado);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
