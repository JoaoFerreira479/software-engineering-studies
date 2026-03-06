package programarcomputadoresvariaveisdados;

public class Data {

    private final int dia;
    private final int mes;
    private final int ano;

    public Data(int dia, int mes, int ano) {
        if (!validarDiaMesAno(dia, mes, ano)) {
            throw new IllegalArgumentException("Data inválida: valores de dia, mês ou ano estão fora dos limites.");
        }
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    private static final int ANO_MINIMO = 0;
    private static final int ANO_MAXIMO = 99;

    private static boolean validarDiaMesAno(int dia, int mes, int ano) {
        return mes >= 1 && mes <= 12
            && ano >= ANO_MINIMO && ano <= ANO_MAXIMO
            && dia >= 1 && dia <= diasNoMes(mes);
    }

    private static int diasNoMes(int mes) {
        return switch (mes) {
            case 2 -> 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }
}
