package laboratorio;

import laboratorio.dominio.Cao;
import laboratorio.dominio.Falante;
import laboratorio.dominio.Gato;
import laboratorio.dominio.Homem;
import laboratorio.estruturas.ListaSequencial;

public final class Guia4 {

    private static final int QUANTIDADE_POR_TIPO = 5;

    private Guia4() {}

    public static void main(String[] args) {
        ListaSequencial<Falante> animais = new ListaSequencial<>(QUANTIDADE_POR_TIPO * 3);
        for (int i = 0; i < QUANTIDADE_POR_TIPO; i++) {
            animais.inserirUltimaPosicao(new Homem("Homem " + (i + 1)));
            animais.inserirUltimaPosicao(new Cao("Cão " + (i + 1)));
            animais.inserirUltimaPosicao(new Gato("Gato " + (i + 1)));
        }
        exibirFalas(animais);
    }

    private static void exibirFalas(ListaSequencial<Falante> animais) {
        System.out.println("Animais falando:");
        animais.percorrer(a -> System.out.println(a.getNome() + ": " + a.getFala()));
    }
}
