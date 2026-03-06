package programarcomputadoresalternativasdecisao;

public final class ValidadorData {

    private static final int TAMANHO_DATA = 10;
    private static final int ANO_MIN = 1900;
    private static final int ANO_MAX = 2100;

    private static final int[] DIAS_NO_MES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private ValidadorData() {
    }

    /**
     * Valida a string no formato DD/MM/AAAA.
     *
     * @param data string com 10 caracteres (DD/MM/AAAA)
     * @return mensagem "Data válida." ou mensagem de erro
     */
    public static String validarData(final String data) {
        if (data == null || data.length() != TAMANHO_DATA) {
            return "Erro: A data deve ter 10 caracteres no formato DD/MM/AAAA.";
        }
        if (data.charAt(2) != '/' || data.charAt(5) != '/') {
            return "Erro: A data deve conter barras ('/') nas posições corretas.";
        }
        try {
            final int dia = Integer.parseInt(data.substring(0, 2));
            final int mes = Integer.parseInt(data.substring(3, 5));
            final int ano = Integer.parseInt(data.substring(6, 10));

            if (mes < 1 || mes > 12) {
                return "Erro: O mês deve estar entre 1 e 12.";
            }
            if (!diaValido(dia, mes, ano)) {
                return "Erro: O dia não é válido para o mês informado.";
            }
            if (ano < ANO_MIN || ano > ANO_MAX) {
                return "Erro: O ano deve estar entre " + ANO_MIN + " e " + ANO_MAX + ".";
            }
            return "Data válida.";
        } catch (NumberFormatException e) {
            return "Erro: A data deve conter apenas números no formato DD/MM/AAAA.";
        }
    }

    private static boolean diaValido(final int dia, final int mes, final int ano) {
        final int maxDias = mes == 2 && anoBissexto(ano) ? 29 : DIAS_NO_MES[mes - 1];
        return dia >= 1 && dia <= maxDias;
    }

    private static boolean anoBissexto(final int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
