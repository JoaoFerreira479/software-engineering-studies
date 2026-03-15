package aed2.application;

import aed2.domain.estruturas.Mapa;
import aed2.domain.model.MedalhistaComContagem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

final class OrdenacaoMainHelper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    static Mapa<String, MedalhistaComContagem> carregarMedalhistas(Path csv) {
        Mapa<String, MedalhistaComContagem> porNome = new Mapa<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(",", -1);
                if (p.length < 7) continue;
                String nome = p[0].trim();
                String genero = p[1].trim();
                LocalDate nascimento = parseData(p[2].trim());
                String pais = p[3].trim();
                int ouro = parseInt(p[4].trim(), 0);
                int prata = parseInt(p[5].trim(), 0);
                int bronze = parseInt(p[6].trim(), 0);
                porNome.inserir(nome, new MedalhistaComContagem(nome, genero, nascimento, pais, ouro, prata, bronze));
            }
        } catch (IOException e) {
            return null;
        }
        return porNome;
    }

    static Comparator<MedalhistaComContagem> comparadorMedalhas() {
        return (m1, m2) -> {
            if (m1.getOuro() != m2.getOuro()) return Integer.compare(m2.getOuro(), m1.getOuro());
            if (m1.getPrata() != m2.getPrata()) return Integer.compare(m2.getPrata(), m1.getPrata());
            if (m1.getBronze() != m2.getBronze()) return Integer.compare(m2.getBronze(), m1.getBronze());
            return m1.getNome().compareToIgnoreCase(m2.getNome());
        };
    }

    private static LocalDate parseData(String s) {
        if (s == null || s.isEmpty()) return LocalDate.EPOCH;
        try {
            return LocalDate.parse(s, ISO);
        } catch (DateTimeParseException e) {
            return LocalDate.EPOCH;
        }
    }

    private static int parseInt(String s, int padrao) {
        if (s == null || s.isEmpty()) return padrao;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return padrao;
        }
    }
}
