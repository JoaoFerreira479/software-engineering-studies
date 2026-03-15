package aed2.application;

import aed2.domain.estruturas.ABB;
import aed2.domain.model.LinhaMedalhista;
import aed2.domain.model.Medalhista;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ABBRecortarMain {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado: " + csv);
            return;
        }
        ABB<Medalhista> abb = carregarABB(csv);
        if (abb == null) return;
        processarRemocoes(abb);
    }

    private static ABB<Medalhista> carregarABB(Path csv) {
        ABB<Medalhista> abb = new ABB<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    if (!abb.contem(lm.toMedalhista())) {
                        abb.adicionar(lm.toMedalhista());
                    }
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return abb;
    }

    private static void processarRemocoes(ABB<Medalhista> abb) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String nome;
            while ((nome = in.readLine()) != null && !nome.trim().equals("FIM")) {
                Medalhista chave = new Medalhista(nome.trim(), "", java.time.LocalDate.EPOCH, "");
                if (abb.contem(chave)) {
                    abb.remover(chave);
                    Console.println("Medalhista removido: " + nome.trim());
                } else {
                    Console.println("Medalhista nao encontrado");
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler entrada: " + e.getMessage());
        }
    }
}
