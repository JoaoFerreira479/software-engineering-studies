package aed2.application;

import aed2.domain.estruturas.ABB;
import aed2.domain.estruturas.TabelaHash;
import aed2.domain.model.Evento;
import aed2.domain.model.LinhaMedalhista;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class GerenciadorEventosHashMain {

    private static final DateTimeFormatter ENTRADA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int CAPACIDADE_HASH = 100;

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado ou nao legivel: " + csv);
            return;
        }
        TabelaHash<LocalDate, ABB<Evento>> tabela = carregarEventosPorData(csv);
        if (tabela == null) return;
        processarConsultas(tabela);
    }

    private static TabelaHash<LocalDate, ABB<Evento>> carregarEventosPorData(Path csv) {
        TabelaHash<LocalDate, ABB<Evento>> tabela = new TabelaHash<>(CAPACIDADE_HASH);
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                LocalDate data = lm.data;
                ABB<Evento> eventos = tabela.pesquisar(data);
                if (eventos == null) {
                    eventos = new ABB<>();
                    tabela.inserir(data, eventos);
                }
                eventos.adicionar(lm.toEventoPorEsporteNome());
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler CSV: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            Console.erro("CSV invalido: " + e.getMessage());
            return null;
        }
        return tabela;
    }

    private static void processarConsultas(TabelaHash<LocalDate, ABB<Evento>> tabela) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String comando;
            while ((comando = in.readLine()) != null && !comando.trim().equals("FIM")) {
                String dataStr = comando.trim();
                LocalDate data = LocalDate.parse(dataStr, ENTRADA);
                Console.println("Eventos do dia " + dataStr);
                ABB<Evento> eventos = tabela.pesquisar(data);
                if (eventos != null) {
                    eventos.emOrdem(e -> Console.println(e.toString()));
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler entrada: " + e.getMessage());
        }
    }
}
