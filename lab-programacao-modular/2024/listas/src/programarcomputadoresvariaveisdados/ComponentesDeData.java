package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class ComponentesDeData {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String entrada = EntradaUtil.lerLinha(scanner, "Digite uma data no formato dd/mm/aa: ");

            if (ManipuladorData.validarFormatoData(entrada)) {
                try {
                    Data data = ManipuladorData.converterParaData(entrada);

                    System.out.println("Dia: " + data.getDia());
                    System.out.println("Mês: " + data.getMes());
                    System.out.println("Ano: " + data.getAno());
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else {
                System.out.println("Erro: Formato de data inválido. Use o formato dd/mm/aa.");
            }
        }
    }
}
