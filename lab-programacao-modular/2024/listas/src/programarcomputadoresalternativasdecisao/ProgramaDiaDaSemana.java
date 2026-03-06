package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaDiaDaSemana {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int numeroDia = EntradaUtil.lerInteiroNoIntervalo(scanner, "Digite um número de 1 a 7 para o dia da semana: ", 1, 7);
            final String nomeDia = DiaSemana.obterNomeDia(numeroDia).orElseThrow();
            System.out.println("O dia correspondente é: " + nomeDia);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
