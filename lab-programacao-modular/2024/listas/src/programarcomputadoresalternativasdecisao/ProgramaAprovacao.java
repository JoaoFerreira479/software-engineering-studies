package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaAprovacao {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String nome = EntradaUtil.lerLinha(scanner, "Digite o nome da disciplina: ");
            if (nome.isEmpty()) {
                System.out.println("Erro: O nome da disciplina não pode ser vazio.");
                return;
            }
            final double notaFinal = EntradaUtil.lerDouble(scanner, "Digite a nota final (deve ser múltipla de 0,5): ");
            final int aulasMinistradas = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de aulas ministradas: ");
            final int faltas = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de faltas: ");

            final Disciplina disciplina = new Disciplina(nome, notaFinal, aulasMinistradas, faltas);
            final String resultado = Aprovacao.verificarAprovacao(disciplina);

            System.out.println("\n=== Resultado ===");
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Nota Final: " + disciplina.getNota());
            System.out.printf("Frequência: %.2f%%%n", disciplina.getFrequencia());
            System.out.println("Resultado: " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
