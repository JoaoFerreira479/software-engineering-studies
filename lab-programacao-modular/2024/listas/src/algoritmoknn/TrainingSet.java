package algoritmoknn;

import java.util.List;

public final class TrainingSet {

    private final List<DataPoint> pontos;

    public TrainingSet(final List<DataPoint> pontos) {
        if (pontos == null) {
            throw new NullPointerException("Lista de pontos não pode ser nula");
        }
        this.pontos = List.copyOf(pontos);
    }

    public List<DataPoint> getPontos() {
        return pontos;
    }

    public int size() {
        return pontos.size();
    }

    public boolean isEmpty() {
        return pontos.isEmpty();
    }
}
