package programarcomputadoresbasicos;

public record FaixaImposto(double limiteInferior, double limiteSuperior, double aliquota) {

    public FaixaImposto {
        if (limiteInferior < 0 || limiteSuperior < limiteInferior) {
            throw new IllegalArgumentException("Limites inválidos da faixa");
        }
        if (aliquota < 0 || aliquota > 1) {
            throw new IllegalArgumentException("Alíquota deve estar entre 0 e 1: " + aliquota);
        }
    }

    public boolean aplica(final double valor) {
        return valor > limiteInferior;
    }

    public double valorNaFaixa(final double valor) {
        final double teto = Math.min(valor, limiteSuperior);
        return Math.max(0, teto - limiteInferior);
    }

    public double impostoNaFaixa(final double valor) {
        return valorNaFaixa(valor) * aliquota;
    }
}
