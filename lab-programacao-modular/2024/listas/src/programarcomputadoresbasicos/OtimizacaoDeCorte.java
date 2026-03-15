package programarcomputadoresbasicos;

import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.List;
import java.util.Scanner;

public final class OtimizacaoDeCorte {

    private static final int COMPRIMENTO_PECA_CM = 45;
    private static final int[] TAMANHOS_TABUAS_CM = { 300, 400, 500 };

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int quantidadePecas = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de peças de 45 cm necessárias: ");
            if (quantidadePecas <= 0) {
                Console.println("Erro: A quantidade deve ser positiva.");
                return;
            }
            final List<ResultadoCorte> resultados = calcularOpcoes(TAMANHOS_TABUAS_CM, COMPRIMENTO_PECA_CM, quantidadePecas);
            exibirResultados(resultados);
        }
    }

    /**
     * Para cada tamanho de tábua, calcula peças por tábua, tábuas necessárias e sobra na última.
     */
    public static List<ResultadoCorte> calcularOpcoes(final int[] tamanhosTabuas, final int comprimentoPeca, final int quantidadePecas) {
        return java.util.Arrays.stream(tamanhosTabuas)
            .mapToObj(t -> calcularCorte(t, comprimentoPeca, quantidadePecas))
            .toList();
    }

    public static ResultadoCorte calcularCorte(final int tamanhoTabua, final int comprimentoPeca, final int quantidadePecas) {
        final int pecasPorTabua = tamanhoTabua / comprimentoPeca;
        if (pecasPorTabua == 0) {
            throw new IllegalArgumentException("Tábua menor que o comprimento da peça: " + tamanhoTabua);
        }
        final int tabuasNecessarias = (int) Math.ceil((double) quantidadePecas / pecasPorTabua);
        final int sobraNaUltimaTabua = (tabuasNecessarias * tamanhoTabua) - (quantidadePecas * comprimentoPeca);
        return new ResultadoCorte(tamanhoTabua, pecasPorTabua, tabuasNecessarias, sobraNaUltimaTabua);
    }

    private static void exibirResultados(final List<ResultadoCorte> resultados) {
        Console.println("\nOpções de corte para as tábuas disponíveis:");
        for (final ResultadoCorte r : resultados) {
            Console.println("Para tábuas de " + r.tamanhoTabua() + " cm:");
            Console.println("- Pedaços por tábua: " + r.pecasPorTabua());
            Console.println("- Tábuas necessárias: " + r.tabuasNecessarias());
            Console.println("- Sobra de madeira na última tábua: " + r.sobraNaUltimaTabua() + " cm\n");
        }
    }
}
