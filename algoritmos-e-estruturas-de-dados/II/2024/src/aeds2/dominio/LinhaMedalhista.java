package aeds2.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class LinhaMedalhista {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    public final String nome;
    public final String genero;
    public final LocalDate nascimento;
    public final String pais;
    public final TipoMedalha tipo;
    public final LocalDate data;
    public final String disciplina;
    public final String evento;

    private LinhaMedalhista(String nome, String genero, LocalDate nascimento, String pais,
                            TipoMedalha tipo, LocalDate data, String disciplina, String evento) {
        this.nome = nome;
        this.genero = genero;
        this.nascimento = nascimento;
        this.pais = pais;
        this.tipo = tipo;
        this.data = data;
        this.disciplina = disciplina;
        this.evento = evento;
    }

    public static LinhaMedalhista parse(String linha) {
        Objects.requireNonNull(linha, "linha");
        String[] p = linha.split(",", -1);
        if (p.length < 8) {
            throw new IllegalArgumentException("Linha CSV invalida (menos de 8 colunas): " + linha);
        }
        String nome = p[0].trim();
        TipoMedalha tipo = parseTipo(p[1].trim());
        LocalDate data = parseData(p[2].trim());
        String genero = p[3].trim();
        LocalDate nascimento = parseData(p[4].trim());
        String pais = p[5].trim();
        String disciplina = p[6].trim();
        String evento = p[7].trim();
        return new LinhaMedalhista(nome, genero, nascimento, pais, tipo, data, disciplina, evento);
    }

    private static TipoMedalha parseTipo(String s) {
        if (s == null || s.isEmpty()) return TipoMedalha.BRONZE;
        switch (s.toUpperCase()) {
            case "GOLD": case "OURO": return TipoMedalha.OURO;
            case "SILVER": case "PRATA": return TipoMedalha.PRATA;
            case "BRONZE": default: return TipoMedalha.BRONZE;
        }
    }

    private static LocalDate parseData(String s) {
        if (s == null || s.isEmpty()) {
            return LocalDate.EPOCH;
        }
        return LocalDate.parse(s, ISO);
    }

    public Medalhista toMedalhista() {
        return new Medalhista(nome, genero, nascimento, pais);
    }

    public Evento toEventoPorData() {
        return new Evento(nome, data);
    }

    public Evento toEventoPorEsporteNome() {
        return new Evento(disciplina, evento);
    }

    public Medalha toMedalha() {
        return new Medalha(tipo, data, disciplina, evento);
    }
}
