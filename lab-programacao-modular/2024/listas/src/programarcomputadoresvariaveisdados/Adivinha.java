package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

public class Adivinha {

    private static final int CONSTANTE_OPERACAO = 3;

    public int calcularNumeroPensado(int resultado) {
        return resultado - CONSTANTE_OPERACAO;
    }

    public void exibirInstrucoes() {
        Console.println("Pense em um número (não informe agora). Vou tentar adivinhar!");
        Console.println("\nAgora siga os passos abaixo:");
        Console.println("1. Multiplique o número pensado por 2.");
        Console.println("2. Some 6 ao resultado.");
        Console.println("3. Divida o resultado por 2.");
    }
}
