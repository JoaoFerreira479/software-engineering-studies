package aeds2.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Medalha {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final TipoMedalha tipo;
    private final LocalDate data;
    private final String disciplina;
    private final String evento;

    public Medalha(TipoMedalha tipo, LocalDate data, String disciplina, String evento) {
        this.tipo = Objects.requireNonNull(tipo, "tipo");
        this.data = data != null ? data : LocalDate.EPOCH;
        this.disciplina = disciplina != null ? disciplina : "";
        this.evento = evento != null ? evento : "";
    }

    public TipoMedalha getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getEvento() {
        return evento;
    }

    @Override
    public String toString() {
        return tipo + " - " + disciplina + " - " + evento + " - " + data.format(FMT);
    }
}
