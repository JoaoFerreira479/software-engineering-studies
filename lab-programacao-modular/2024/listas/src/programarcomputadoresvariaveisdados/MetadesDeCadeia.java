package programarcomputadoresvariaveisdados;

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
                System.out.println("Erro: A cadeia deve ter no máximo " + ManipuladorCadeia.TAMANHO_MAXIMO
                        + " caracteres e não pode ser vazia.");
            }

            String[] metades = ManipuladorCadeia.dividirCadeia(cadeia);

            System.out.println("Primeira metade: " + metades[0]);
            System.out.println("Segunda metade: " + metades[1]);
        }
    }
}
