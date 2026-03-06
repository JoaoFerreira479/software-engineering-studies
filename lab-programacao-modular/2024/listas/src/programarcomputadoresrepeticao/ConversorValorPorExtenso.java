package programarcomputadoresrepeticao;

public final class ConversorValorPorExtenso {

    private static final String[] UNIDADES = { "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove" };
    private static final String[] DEZENAS = { "", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
    private static final String[] DEZENAS_ESPECIAIS = { "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove" };
    private static final String[] CENTENAS = { "", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };

    private static final double VALOR_MAX = 999_999.99;

    private ConversorValorPorExtenso() {
    }

    public static String converterValorMonetario(final double valor) {
        if (valor < 0 || valor > VALOR_MAX) {
            throw new IllegalArgumentException("O valor deve estar entre 0 e 999.999,99.");
        }
        final int parteInteira = (int) valor;
        final int centavos = (int) Math.round((valor - parteInteira) * 100);
        final String textoReais = converterParteInteira(parteInteira);
        final String textoCentavos = converterCentavos(centavos);
        return textoCentavos.isEmpty() ? textoReais : textoReais + " e " + textoCentavos;
    }

    public static String converterParteInteira(final int valor) {
        if (valor == 0) return "zero reais";
        final StringBuilder sb = new StringBuilder();
        final int centenas = valor / 100;
        final int resto = valor % 100;
        if (centenas > 0) {
            sb.append(centenas == 1 && resto == 0 ? "cem" : CENTENAS[centenas]);
            if (resto > 0) sb.append(" e ");
        }
        if (resto >= 10 && resto <= 19) {
            sb.append(DEZENAS_ESPECIAIS[resto - 10]);
        } else {
            final int dezenas = resto / 10;
            final int unidades = resto % 10;
            if (dezenas > 0) {
                sb.append(DEZENAS[dezenas]);
                if (unidades > 0) sb.append(" e ");
            }
            if (unidades > 0) sb.append(UNIDADES[unidades]);
        }
        sb.append(" reais");
        return sb.toString();
    }

    public static String converterCentavos(final int centavos) {
        if (centavos == 0) return "";
        if (centavos >= 10 && centavos <= 19) {
            return DEZENAS_ESPECIAIS[centavos - 10] + " centavos";
        }
        final StringBuilder sb = new StringBuilder();
        final int dezenas = centavos / 10;
        final int unidades = centavos % 10;
        if (dezenas > 0) {
            sb.append(DEZENAS[dezenas]);
            if (unidades > 0) sb.append(" e ");
        }
        if (unidades > 0) sb.append(UNIDADES[unidades]);
        sb.append(" centavos");
        return sb.toString();
    }
}
