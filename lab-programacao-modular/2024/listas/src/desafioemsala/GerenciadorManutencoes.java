package desafioemsala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public final class GerenciadorManutencoes {

    private Map<String, List<Manutencao>> manutencoesPorTipo;

    public GerenciadorManutencoes() {
        this.manutencoesPorTipo = new HashMap<>();
    }

    public void adicionarManutencao(final Manutencao manutencao) {
        Objects.requireNonNull(manutencao, "Manutenção não pode ser nula");
        final String chave = manutencao.getTipoServicoNormalizado();
        manutencoesPorTipo.computeIfAbsent(chave, k -> new ArrayList<>()).add(manutencao);
    }

    public Map<String, List<Manutencao>> getTodasManutencoes() {
        final Map<String, List<Manutencao>> copia = new HashMap<>();
        manutencoesPorTipo.forEach((k, v) -> copia.put(k, List.copyOf(v)));
        return Collections.unmodifiableMap(copia);
    }

    public List<Manutencao> getManutencoesPorTipo(final String tipoServico) {
        if (tipoServico == null || tipoServico.isBlank()) {
            return List.of();
        }
        final List<Manutencao> lista = manutencoesPorTipo.get(tipoServico.trim().toLowerCase(Locale.ROOT));
        return lista == null ? List.of() : List.copyOf(lista);
    }

    public boolean removerManutencao(final String tipoServico, final LocalDate data) {
        if (tipoServico == null || tipoServico.isBlank() || data == null) {
            return false;
        }
        final String chave = tipoServico.trim().toLowerCase(Locale.ROOT);
        final List<Manutencao> lista = manutencoesPorTipo.get(chave);
        if (lista == null) return false;
        final boolean removido = lista.removeIf(m -> m.getData().equals(data));
        if (removido && lista.isEmpty()) {
            manutencoesPorTipo.remove(chave);
        }
        return removido;
    }

    public boolean isEmpty() {
        return manutencoesPorTipo.isEmpty();
    }

    public void salvarEmArquivo(final Path caminho) throws IOException {
        Objects.requireNonNull(caminho, "Caminho não pode ser nulo");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(caminho))) {
            oos.writeObject(manutencoesPorTipo);
        }
    }

    public void carregarDeArquivo(final Path caminho) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(caminho, "Caminho não pode ser nulo");
        if (!Files.isRegularFile(caminho)) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(caminho))) {
            final Object obj = ois.readObject();
            if (!(obj instanceof Map)) {
                return;
            }
            final Map<?, ?> raw = (Map<?, ?>) obj;
            final Map<String, List<Manutencao>> carregado = new HashMap<>();
            for (final Map.Entry<?, ?> e : raw.entrySet()) {
                if (!(e.getKey() instanceof String key) || !(e.getValue() instanceof List<?> list)) {
                    continue;
                }
                final List<Manutencao> manutencoes = new ArrayList<>();
                for (final Object item : list) {
                    if (item instanceof Manutencao m) {
                        manutencoes.add(m);
                    }
                }
                if (!manutencoes.isEmpty()) {
                    carregado.put(key, manutencoes);
                }
            }
            this.manutencoesPorTipo = carregado;
        }
    }
}
