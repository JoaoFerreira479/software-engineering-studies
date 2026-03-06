package algoritmoknn;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public final class KnnExample {

    private static final String ARQUIVO_PADRAO = "feliz.csv";
    private static final int K_PADRAO = 3;

    public static void main(final String[] args) {
        final String caminhoCsv = args.length > 0 ? args[0].trim() : ARQUIVO_PADRAO;
        final Path path = Path.of(caminhoCsv);

        try {
            final var pontos = CsvDatasetLoader.carregar(path);
            final var trainingSet = new TrainingSet(pontos);
            final var classifier = new KnnClassifier(trainingSet, K_PADRAO);

            executarInterativo(classifier);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de dados: " + e.getMessage());
        }
    }

    private static void executarInterativo(final KnnClassifier classifier) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double horasSono = lerDouble(
                scanner,
                "Digite as horas de sono (0 a " + (int) DomainConstants.MAX_HORAS_SONO + "): ",
                0,
                DomainConstants.MAX_HORAS_SONO);
            final double calorias = lerDouble(
                scanner,
                "Digite a quantidade de calorias (0 a " + (int) DomainConstants.MAX_CALORIAS + "): ",
                0,
                DomainConstants.MAX_CALORIAS);

            final String estado = classifier.classify(horasSono, calorias);
            System.out.println("Classificação: " + estado);
        }
    }

    private static double lerDouble(
            final Scanner scanner,
            final String prompt,
            final double min,
            final double max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextDouble()) {
                scanner.next();
                System.out.println("Valor inválido. Digite um número.");
                continue;
            }
            final double valor = scanner.nextDouble();
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("Valor fora do intervalo [" + min + ", " + max + "]. Tente novamente.");
        }
    }
}
