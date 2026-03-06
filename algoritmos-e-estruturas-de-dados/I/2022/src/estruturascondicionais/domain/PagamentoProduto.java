package estruturascondicionais.domain;

public final class PagamentoProduto {

    private static final double DESCONTO_AVISTA = 0.90;
    private static final double JUROS_PARCELADO = 1.15;
    private static final int MAX_PARCELAS_SEM_JUROS = 3;
    private static final int MAX_PARCELAS_COM_JUROS = 10;

    private PagamentoProduto() {
    }

    public static void validarValorProduto(final double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }
    }

    public static void validarOpcao(final int opcao) {
        if (opcao < 1 || opcao > 4) {
            throw new IllegalArgumentException("Opção inválida. Escolha entre 1 e 4.");
        }
    }

    public static double valorAVistaComDesconto(final double valorProduto) {
        validarValorProduto(valorProduto);
        return valorProduto * DESCONTO_AVISTA;
    }

    public static double valorAVistaSemDesconto(final double valorProduto) {
        validarValorProduto(valorProduto);
        return valorProduto;
    }

    public static ResultadoParcelado parceladoSemJuros(final double valorProduto, final int parcelas) {
        validarValorProduto(valorProduto);
        if (parcelas < 1 || parcelas > MAX_PARCELAS_SEM_JUROS) {
            throw new IllegalArgumentException(
                    "Número de parcelas inválido. Escolha entre 1 e " + MAX_PARCELAS_SEM_JUROS + ".");
        }
        return new ResultadoParcelado(valorProduto, valorProduto / parcelas, parcelas);
    }

    public static ResultadoParcelado parceladoComJuros(final double valorProduto, final int parcelas) {
        validarValorProduto(valorProduto);
        if (parcelas < 1 || parcelas > MAX_PARCELAS_COM_JUROS) {
            throw new IllegalArgumentException(
                    "Número de parcelas inválido. Escolha entre 1 e " + MAX_PARCELAS_COM_JUROS + ".");
        }
        double valorTotal = valorProduto * JUROS_PARCELADO;
        return new ResultadoParcelado(valorTotal, valorTotal / parcelas, parcelas);
    }

    public record ResultadoParcelado(double valorTotal, double valorParcela, int parcelas) {
    }
}
