package praticaabbdeabb;

import aeds2.dominio.Evento;
import aeds2.dominio.LinhaMedalhista;
import aeds2.estruturas.ABB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class GerenciadorEventos {

    private static final DateTimeFormatter ENTRADA_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado: " + csv);
            return;
        }
        ABB<Evento> abb = carregarEventos(csv);
        if (abb == null) return;
        processarComandos(abb);
    }

    private static ABB<Evento> carregarEventos(Path csv) {
        ABB<Evento> abb = new ABB<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    abb.adicionar(lm.toEventoPorData());
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return abb;
    }

    private static void processarComandos(ABB<Evento> abb) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String comando;
            while ((comando = in.readLine()) != null && !comando.trim().equals("FIM")) {
                String cmd = comando.trim();
                if (cmd.startsWith("PESQUISAR")) {
                    String nomeEvento = cmd.substring(9).trim();
                    Evento encontrado = pesquisarPorNome(abb, nomeEvento);
                    if (encontrado != null) {
                        System.out.println("Evento encontrado: " + encontrado);
                    } else {
                        System.out.println("Evento nao encontrado");
                    }
                } else if (cmd.startsWith("IMPRIMIR")) {
                    String dataStr = cmd.substring(8).trim();
                    LocalDate data = LocalDate.parse(dataStr, ENTRADA_DATA);
                    imprimirEventosPorData(abb, data);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler entrada: " + e.getMessage());
        }
    }

    private static Evento pesquisarPorNome(ABB<Evento> abb, String nome) {
        final Evento[] resultado = new Evento[1];
        abb.emOrdem(e -> {
            if (resultado[0] == null && e.getNome().equals(nome)) {
                resultado[0] = e;
            }
        });
        return resultado[0];
    }

    private static void imprimirEventosPorData(ABB<Evento> abb, LocalDate data) {
        System.out.println("Eventos do dia " + data.format(ENTRADA_DATA));
        abb.emOrdem(e -> {
            if (e.getData().equals(data)) {
                System.out.println(e.getNome());
            }
        });
    }
}
