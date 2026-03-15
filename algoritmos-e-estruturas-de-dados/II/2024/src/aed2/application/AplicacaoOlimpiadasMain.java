package aed2.application;

import aed2.domain.estruturas.ABB;
import aed2.domain.model.LinhaMedalhista;
import aed2.domain.model.MedalhistaComMedalhas;
import aed2.domain.model.TipoMedalha;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AplicacaoOlimpiadasMain {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado: " + csv);
            return;
        }
        ABB<MedalhistaComMedalhas> porNome = carregarMedalhistas(csv);
        if (porNome == null) return;
        processarConsultas(porNome);
    }

    private static ABB<MedalhistaComMedalhas> carregarMedalhistas(Path csv) {
        ABB<MedalhistaComMedalhas> porNome = new ABB<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    MedalhistaComMedalhas m = buscarOuCriar(porNome, lm.nome, lm.genero, lm.nascimento, lm.pais);
                    m.incluirMedalha(lm.toMedalha());
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return porNome;
    }

    private static MedalhistaComMedalhas buscarOuCriar(ABB<MedalhistaComMedalhas> abb, String nome,
                                                       String genero, java.time.LocalDate nascimento, String pais) {
        MedalhistaComMedalhas chave = new MedalhistaComMedalhas(nome, genero, nascimento, pais);
        if (abb.contem(chave)) {
            return abb.pesquisar(chave);
        }
        abb.adicionar(chave);
        return chave;
    }

    private static void processarConsultas(ABB<MedalhistaComMedalhas> porNome) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String linha;
            while ((linha = in.readLine()) != null && !linha.trim().equals("FIM")) {
                String[] partes = linha.split(",", 2);
                if (partes.length < 2) continue;
                String nome = partes[0].trim();
                String tipoStr = partes[1].trim().toUpperCase();
                TipoMedalha tipo;
                try {
                    tipo = TipoMedalha.valueOf(tipoStr);
                } catch (IllegalArgumentException e) {
                    continue;
                }
                MedalhistaComMedalhas chave = new MedalhistaComMedalhas(nome, "", java.time.LocalDate.EPOCH, "");
                if (porNome.contem(chave)) {
                    MedalhistaComMedalhas m = porNome.pesquisar(chave);
                    Console.println(m.toString());
                    Console.println(m.relatorioDeMedalhas(tipo));
                    Console.println("");
                }
            }
        } catch (IOException e) {
            Console.erro("Erro ao ler entrada: " + e.getMessage());
        }
    }
}
