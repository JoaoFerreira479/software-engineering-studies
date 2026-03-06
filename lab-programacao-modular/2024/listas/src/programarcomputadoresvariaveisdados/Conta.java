package programarcomputadoresvariaveisdados;

public class Conta {

    private final int impulsosExcedentes;
    private final int impulsosInterurbanos;
    private final int impulsosCelular;

    private double totalServicos;
    private final StringBuilder servicosDetalhados = new StringBuilder();

    public Conta(int impulsosExcedentes, int impulsosInterurbanos, int impulsosCelular) {
        if (impulsosExcedentes < 0 || impulsosInterurbanos < 0 || impulsosCelular < 0) {
            throw new IllegalArgumentException("Quantidade de impulsos não pode ser negativa.");
        }
        this.impulsosExcedentes = impulsosExcedentes;
        this.impulsosInterurbanos = impulsosInterurbanos;
        this.impulsosCelular = impulsosCelular;
    }

    public int getImpulsosExcedentes() {
        return impulsosExcedentes;
    }

    public int getImpulsosInterurbanos() {
        return impulsosInterurbanos;
    }

    public int getImpulsosCelular() {
        return impulsosCelular;
    }

    public void adicionarServico(String nomeServico, int quantidade, double custoPorUnidade) {
        double custo = quantidade * custoPorUnidade;
        totalServicos += custo;
        servicosDetalhados.append(String.format("%s: %d impulsos (Custo: R$ %.2f)\n", nomeServico, quantidade, custo));
    }

    public double calcularTotal(double assinatura) {
        return assinatura + totalServicos;
    }

    public void exibirServicos() {
        System.out.print(servicosDetalhados.toString());
    }
}
