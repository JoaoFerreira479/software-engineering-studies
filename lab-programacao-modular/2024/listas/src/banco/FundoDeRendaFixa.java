package banco;

public final class FundoDeRendaFixa extends Conta {

    private final double taxaAdministracao;

    public FundoDeRendaFixa(final String numero, final double saldoInicial, final double taxaAdministracao) {
        super(numero, saldoInicial);
        if (taxaAdministracao < 0 || taxaAdministracao > 1) {
            throw new IllegalArgumentException(
                "Taxa de administração deve estar entre 0 e 1 (ex.: 0,01 para 1%): " + taxaAdministracao);
        }
        this.taxaAdministracao = taxaAdministracao;
    }

    public double getTaxaAdministracao() {
        return taxaAdministracao;
    }

    @Override
    public String gerarExtrato() {
        return "Fundo de Renda Fixa: " + getNumero() + "\nSaldo: " + getSaldo()
            + "\nTaxa de Administração: " + taxaAdministracao;
    }
}
