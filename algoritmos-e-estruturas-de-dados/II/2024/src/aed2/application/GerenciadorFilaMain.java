package aed2.application;

import aed2.domain.estruturas.Fila;
import aed2.domain.model.LinhaMedalhista;
import aed2.domain.model.Medalhista;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public final class GerenciadorFilaMain {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado: " + csv);
            return;
        }
        Fila<Medalhista> fila = carregarFila(csv);
        if (fila == null) return;
        processarComandos(fila);
    }

    private static Fila<Medalhista> carregarFila(Path csv) {
        Fila<Medalhista> fila = new Fila<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    fila.enfileirar(lm.toMedalhista());
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return fila;
    }

    private static void processarComandos(Fila<Medalhista> fila) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String linha;
            while ((linha = in.readLine()) != null) {
                if (linha.trim().equals("FIM")) break;
                String[] partes = linha.split(" ", 2);
                String cmd = partes[0].trim();
                String arg = partes.length > 1 ? partes[1].trim() : "";

                switch (cmd) {
                    case "ENFILEIRAR" -> fila.enfileirar(new Medalhista(arg, "DESCONHECIDO", LocalDate.now(), "DESCONHECIDO"));
                    case "DESENFILEIRAR" -> Console.println("(DESENFILEIRADO) " + fila.desenfileirar().getNome());
                    case "EXISTE" -> {
                        boolean existe = fila.contem(new Medalhista(arg, "", LocalDate.EPOCH, ""));
                        Console.println(arg + " EXISTE NA FILA? " + (existe ? "SIM" : "NAO"));
                    }
                    case "DIVIDIR" -> {
                        Fila<Medalhista> novaFila = fila.dividir();
                        Console.println("FILA ORIGINAL");
                        fila.paraCada(m -> Console.println(m.toString()));
                        Console.println("FILA NOVA");
                        novaFila.paraCada(m -> Console.println(m.toString()));
                    }
                    default -> { }
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler entrada: " + e.getMessage());
        }
    }
}
