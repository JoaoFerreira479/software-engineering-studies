package desafioemsala;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public final class Manutencao implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String tipoServico;
    private final LocalDate data;
    private final String nomeResponsavel;
    private final int prazoGarantia;
    private final String observacoes;

    public Manutencao(
            final String tipoServico,
            final LocalDate data,
            final String nomeResponsavel,
            final int prazoGarantia,
            final String observacoes) {
        if (tipoServico == null || tipoServico.isBlank()) {
            throw new IllegalArgumentException("Tipo de serviço não pode ser nulo ou vazio");
        }
        if (data == null) {
            throw new NullPointerException("Data não pode ser nula");
        }
        if (nomeResponsavel == null || nomeResponsavel.isBlank()) {
            throw new IllegalArgumentException("Nome do responsável não pode ser nulo ou vazio");
        }
        if (prazoGarantia <= 0) {
            throw new IllegalArgumentException("Prazo de garantia deve ser positivo (meses): " + prazoGarantia);
        }
        this.tipoServico = tipoServico.trim();
        this.data = data;
        this.nomeResponsavel = nomeResponsavel.trim();
        this.prazoGarantia = prazoGarantia;
        this.observacoes = observacoes == null ? "" : observacoes;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public LocalDate getData() {
        return data;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public int getPrazoGarantia() {
        return prazoGarantia;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getTipoServicoNormalizado() {
        return tipoServico.toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return "Manutenção: " + tipoServico + "\nData: " + data + "\nResponsável: " + nomeResponsavel
            + "\nGarantia: " + prazoGarantia + " meses" + "\nObservações: " + observacoes + "\n";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Manutencao other)) return false;
        return prazoGarantia == other.prazoGarantia
            && Objects.equals(tipoServico, other.tipoServico)
            && Objects.equals(data, other.data)
            && Objects.equals(nomeResponsavel, other.nomeResponsavel)
            && Objects.equals(observacoes, other.observacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoServico, data, nomeResponsavel, prazoGarantia, observacoes);
    }
}
