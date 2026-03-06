package algoritmoknn;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class KnnClassifier {

    private final TrainingSet trainingSet;
    private final int k;

    public KnnClassifier(final TrainingSet trainingSet, final int k) {
        if (trainingSet == null) {
            throw new NullPointerException("Conjunto de treino não pode ser nulo");
        }
        if (trainingSet.isEmpty()) {
            throw new IllegalArgumentException("Conjunto de treino não pode ser vazio");
        }
        if (k < 1) {
            throw new IllegalArgumentException("k deve ser pelo menos 1: " + k);
        }
        if (k > trainingSet.size()) {
            throw new IllegalArgumentException(
                "k não pode ser maior que o tamanho do conjunto (" + trainingSet.size() + "): " + k);
        }
        this.trainingSet = trainingSet;
        this.k = k;
    }

    public String classify(final double horasSono, final double calorias) {
        DomainConstants.validarEntrada(calorias, horasSono);

        final List<DataPoint> pontos = trainingSet.getPontos();
        final List<DataPoint> vizinhos = pontos.stream()
            .sorted(Comparator.comparingDouble(p -> distanciaNormalizada(p, calorias, horasSono)))
            .limit(k)
            .toList();

        final String classe = votacaoMajoritaria(vizinhos);
        if (classe == null) {
            throw new IllegalStateException("Nenhum vizinho para votação (k=" + k + ")");
        }
        return classe;
    }

    private static double distanciaNormalizada(
            final DataPoint p,
            final double calorias,
            final double horasSono) {
        final double dc = (p.calorias() / DomainConstants.MAX_CALORIAS)
            - (calorias / DomainConstants.MAX_CALORIAS);
        final double ds = (p.horasSono() / DomainConstants.MAX_HORAS_SONO)
            - (horasSono / DomainConstants.MAX_HORAS_SONO);
        return Math.sqrt(dc * dc + ds * ds);
    }

    private static String votacaoMajoritaria(final List<DataPoint> vizinhos) {
        final Map<String, Long> contagens = vizinhos.stream()
            .collect(Collectors.groupingBy(DataPoint::estado, Collectors.counting()));

        final Optional<Map.Entry<String, Long>> max = contagens.entrySet().stream()
            .max(Map.Entry.comparingByValue());

        return max.map(Map.Entry::getKey).orElse(null);
    }
}
