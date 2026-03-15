package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public class MetadesDeCadeia {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String cadeia;
            while (true) {
                cadeia = EntradaUtil.lerLinha(scanner,
                        "Digite uma cadeia de caracteres (até " + ManipuladorCadeia.TAMANHO_MAXIMO + " caracteres): ");

                if (ManipuladorCadeia.validarCadeia(cadeia)) {
                    break;
                }
                Console.println("Erro: A cadeia deve ter no máximo " + ManipuladorCadeia.TAMANHO_MAXIMO
                        + " caracteres e não pode ser vazia.");
            }

            String[] metades = ManipuladorCadeia.dividirCadeia(cadeia);

            Console.println("Primeira metade: " + metades[0]);
            Console.println("Segunda metade: " + metades[1]);
        }
    }
}
