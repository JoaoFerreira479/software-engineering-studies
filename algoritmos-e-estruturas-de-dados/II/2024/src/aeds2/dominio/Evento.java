package aeds2.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Evento implements Comparable<Evento> {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String nome;
    private final LocalDate data;
    private final String esporte;

    public Evento(String nome, LocalDate data) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.data = data != null ? data : LocalDate.EPOCH;
        this.esporte = "";
    }

    public Evento(String esporte, String nome) {
        this.esporte = Objects.requireNonNull(esporte, "esporte");
        this.nome = Objects.requireNonNull(nome, "nome");
        this.data = LocalDate.EPOCH;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getEsporte() {
        return esporte;
    }

    @Override
    public int compareTo(Evento outro) {
        if (!esporte.isEmpty()) {
            int c = esporte.compareTo(outro.esporte);
            return c != 0 ? c : nome.compareTo(outro.nome);
        }
        return data.compareTo(outro.data);
    }

    @Override
    public String toString() {
        if (!esporte.isEmpty()) {
            return esporte + " - " + nome;
        }
        return data.format(FMT) + " - " + nome;
    }
}
