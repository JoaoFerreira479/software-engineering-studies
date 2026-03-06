package programarcomputadoresvariaveisdados;

public class Adivinha {

    private static final int CONSTANTE_OPERACAO = 3;

    public int calcularNumeroPensado(int resultado) {
        return resultado - CONSTANTE_OPERACAO;
    }

    public void exibirInstrucoes() {
        System.out.println("Pense em um número (não informe agora). Vou tentar adivinhar!");
        System.out.println("\nAgora siga os passos abaixo:");
        System.out.println("1. Multiplique o número pensado por 2.");
        System.out.println("2. Some 6 ao resultado.");
        System.out.println("3. Divida o resultado por 2.");
    }
}
