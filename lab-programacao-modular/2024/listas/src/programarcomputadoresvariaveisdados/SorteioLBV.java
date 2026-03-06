package programarcomputadoresvariaveisdados;

public final class SorteioLBV {

    private SorteioLBV() {
    }

    public static String gerarNumeroSorteado(Premio premio1, Premio premio2) {
        if (premio1 == null || premio2 == null) {
            throw new IllegalArgumentException("Prêmios não podem ser nulos.");
        }
        return premio1.getTresUltimosDigitos() + premio2.getTresUltimosDigitos();
    }
}
