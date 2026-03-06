package programarcomputadoresalternativasdecisao;

public record Tarifas(double valorAssinatura, double custoImpulsoExtra, double custoChamadaCelular) {

    private static final double ASSINATURA_PADRAO = 17.90;
    private static final double CUSTO_IMPULSO_PADRAO = 0.04;
    private static final double CUSTO_CELULAR_PADRAO = 0.09;

    public Tarifas {
        if (valorAssinatura < 0 || custoImpulsoExtra < 0 || custoChamadaCelular < 0) {
            throw new IllegalArgumentException("As tarifas devem ser não negativas.");
        }
    }

    public static Tarifas padrao() {
        return new Tarifas(ASSINATURA_PADRAO, CUSTO_IMPULSO_PADRAO, CUSTO_CELULAR_PADRAO);
    }
}
