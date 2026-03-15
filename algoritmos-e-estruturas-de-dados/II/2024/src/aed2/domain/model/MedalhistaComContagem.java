package aed2.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public final class MedalhistaComContagem implements Comparable<MedalhistaComContagem> {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String nome;
    private final String genero;
    private final LocalDate nascimento;
    private final String pais;
    private final int ouro;
    private final int prata;
    private final int bronze;

    public MedalhistaComContagem(String nome, String genero, LocalDate nascimento, String pais,
                                 int ouro, int prata, int bronze) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.genero = genero != null ? genero : "";
        this.nascimento = nascimento != null ? nascimento : LocalDate.EPOCH;
        this.pais = pais != null ? pais : "";
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }

    public String getNome() {
        return nome;
    }

    public int getOuro() {
        return ouro;
    }

    public int getPrata() {
        return prata;
    }

    public int getBronze() {
        return bronze;
    }

    @Override
    public int compareTo(MedalhistaComContagem outro) {
        if (ouro != outro.ouro) return Integer.compare(outro.ouro, ouro);
        if (prata != outro.prata) return Integer.compare(outro.prata, prata);
        if (bronze != outro.bronze) return Integer.compare(outro.bronze, bronze);
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT) + ". Pais: " + pais
                + "\nQuantidade de medalhas de ouro: " + ouro
                + (prata > 0 ? "\nQuantidade de medalhas de prata: " + prata : "")
                + (bronze > 0 ? "\nQuantidade de medalhas de bronze: " + bronze : "") + "\n";
    }
}
