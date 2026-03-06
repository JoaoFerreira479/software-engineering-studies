package praticafila;

import aeds2.dominio.LinhaMedalhista;
import aeds2.dominio.Medalhista;
import aeds2.estruturas.Fila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public final class GerenciadorFila {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado: " + csv);
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
            System.err.println("Erro ao ler CSV: " + e.getMessage());
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
                    case "DESENFILEIRAR" -> System.out.println("(DESENFILEIRADO) " + fila.desenfileirar().getNome());
                    case "EXISTE" -> {
                        boolean existe = fila.contem(new Medalhista(arg, "", LocalDate.EPOCH, ""));
                        System.out.println(arg + " EXISTE NA FILA? " + (existe ? "SIM" : "NAO"));
                    }
                    case "DIVIDIR" -> {
                        Fila<Medalhista> novaFila = fila.dividir();
                        System.out.println("FILA ORIGINAL");
                        fila.paraCada(System.out::println);
                        System.out.println("FILA NOVA");
                        novaFila.paraCada(System.out::println);
                    }
                    default -> { }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler entrada: " + e.getMessage());
        }
    }
}
