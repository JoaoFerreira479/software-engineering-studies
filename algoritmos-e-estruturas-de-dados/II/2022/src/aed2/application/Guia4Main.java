package aed2.application;

import aed2.domain.estruturas.ListaSequencial;
import aed2.domain.model.Cao;
import aed2.domain.model.Falante;
import aed2.domain.model.Gato;
import aed2.domain.model.Homem;
import aed2.infrastructure.io.Console;

public final class Guia4Main {

    private static final int QUANTIDADE_POR_TIPO = 5;

    private Guia4Main() {}

    public static void main(String[] args) {
        ListaSequencial<Falante> animais = new ListaSequencial<>(QUANTIDADE_POR_TIPO * 3);
        for (int i = 0; i < QUANTIDADE_POR_TIPO; i++) {
            animais.inserirUltimaPosicao(new Homem("Homem " + (i + 1)));
            animais.inserirUltimaPosicao(new Cao("Cão " + (i + 1)));
            animais.inserirUltimaPosicao(new Gato("Gato " + (i + 1)));
        }
        Console.println("Animais falando:");
        animais.percorrer(a -> Console.println(a.getNome() + ": " + a.getFala()));
    }
}
