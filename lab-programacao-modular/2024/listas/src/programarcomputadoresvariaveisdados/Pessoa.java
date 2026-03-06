package programarcomputadoresvariaveisdados;

public class Pessoa {

    private final String nomeCompleto;
    private final String iniciais;

    public Pessoa(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new IllegalArgumentException("Nome completo não pode ser vazio.");
        }
        this.nomeCompleto = nomeCompleto.trim();
        this.iniciais = calcularIniciais(this.nomeCompleto);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getIniciais() {
        return iniciais;
    }

    private static String calcularIniciais(String nomeCompleto) {
        String[] palavras = nomeCompleto.trim().split("\\s+");
        if (palavras.length < 2) {
            throw new IllegalArgumentException("É necessário informar pelo menos o nome e o sobrenome.");
        }
        StringBuilder iniciaisBuilder = new StringBuilder();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                iniciaisBuilder.append(palavra.toUpperCase().charAt(0));
            }
        }
        return iniciaisBuilder.toString();
    }
}
