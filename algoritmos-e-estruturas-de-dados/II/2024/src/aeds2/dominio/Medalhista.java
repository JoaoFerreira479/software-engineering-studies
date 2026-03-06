package aeds2.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Medalhista implements Comparable<Medalhista> {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String nome;
    private final String genero;
    private final LocalDate nascimento;
    private final String pais;

    public Medalhista(String nome, String genero, LocalDate nascimento, String pais) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.genero = genero != null ? genero : "";
        this.nascimento = nascimento != null ? nascimento : LocalDate.EPOCH;
        this.pais = pais != null ? pais : "";
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public int compareTo(Medalhista outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medalhista that = (Medalhista) obj;
        return nome.equalsIgnoreCase(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT) + ". Pais: " + pais;
    }
}
