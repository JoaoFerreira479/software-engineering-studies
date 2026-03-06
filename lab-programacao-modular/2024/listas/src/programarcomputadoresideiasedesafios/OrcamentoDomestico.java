package programarcomputadoresideiasedesafios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OrcamentoDomestico {

    private final List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(final TipoTransacao tipo, final String categoria, final double valor, final LocalDate data) {
        transacoes.add(new Transacao(tipo, categoria, valor, data));
    }

    public double calcularSaldo() {
        return transacoes.stream().mapToDouble(Transacao::valorComSinal).sum();
    }

    public List<Transacao> listarTransacoes() {
        return List.copyOf(transacoes);
    }

    public Map<String, Double> calcularResumoPorCategoria() {
        final Map<String, Double> resumo = new HashMap<>();
        for (final Transacao t : transacoes) {
            resumo.merge(t.categoria(), t.valorComSinal(), Double::sum);
        }
        return Map.copyOf(resumo);
    }

    public Map<Integer, Double> calcularResumoMensal(final int ano) {
        final Map<Integer, Double> resumo = new HashMap<>();
        for (final Transacao t : transacoes) {
            if (t.data().getYear() == ano) {
                resumo.merge(t.data().getMonthValue(), t.valorComSinal(), Double::sum);
            }
        }
        return Map.copyOf(resumo);
    }
}
