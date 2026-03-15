package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaValidadorCPF {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                try {
                    final String cpf = EntradaUtil.lerLinha(scanner, "Digite os 9 primeiros dígitos do CPF: ");
                    ValidadorCPF.validarCPF(cpf);
                    final int dv1 = ValidadorCPF.calcularPrimeiroDigito(cpf);
                    final int dv2 = ValidadorCPF.calcularSegundoDigito(cpf, dv1);
                    Console.printf("O CPF completo com os dígitos verificadores é: %s-%d%d%n", cpf, dv1, dv2);
                } catch (IllegalArgumentException e) {
                    Console.println("Erro: " + e.getMessage());
                }
                continuar = EntradaUtil.perguntarSimNao(scanner, "Deseja verificar outro CPF? (S/N): ");
            }
            Console.println("Programa encerrado.");
        }
    }
}
