package com.food.food;

import com.food.food.models.Cardapio;
import com.food.food.models.Cliente;
import com.food.food.models.ItemCardapio;
import com.food.food.models.Mesa;
import com.food.food.models.Restaurante;
import com.food.food.repositories.CardapioRepository;
import com.food.food.repositories.ClienteRepository;
import com.food.food.repositories.ItemCardapioRepository;
import com.food.food.repositories.MesaRepository;
import com.food.food.repositories.RestauranteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private static final int MESAS_CAPACIDADE_4 = 4;
    private static final int MESAS_CAPACIDADE_6 = 4;
    private static final int MESAS_CAPACIDADE_8 = 2;
    private static final int CAPACIDADE_MESA_PEQUENA = 4;
    private static final int CAPACIDADE_MESA_MEDIA = 6;
    private static final int CAPACIDADE_MESA_GRANDE = 8;
    private static final int QUANTIDADE_CLIENTES_INICIAIS = 11;
    private static final String NOME_RESTAURANTE = "MundoFood";
    private static final String ENDERECO_RESTAURANTE = "Savassi, Belo Horizonte, MG, Brasil";

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @PostConstruct
    public void init() {
        if (mesaRepository.count() == 0) {
            for (int i = 0; i < MESAS_CAPACIDADE_4; i++) {
                mesaRepository.save(new Mesa(CAPACIDADE_MESA_PEQUENA));
            }
            for (int i = 0; i < MESAS_CAPACIDADE_6; i++) {
                mesaRepository.save(new Mesa(CAPACIDADE_MESA_MEDIA));
            }
            for (int i = 0; i < MESAS_CAPACIDADE_8; i++) {
                mesaRepository.save(new Mesa(CAPACIDADE_MESA_GRANDE));
            }
        }

        if (clienteRepository.count() == 0) {
            for (int i = 1; i <= QUANTIDADE_CLIENTES_INICIAIS; i++) {
                clienteRepository.save(new Cliente(null, "Cliente " + i, "123456789" + i));
            }
        }

        Optional<Restaurante> restaurante = restauranteRepository.findByNome(NOME_RESTAURANTE);
        if (restaurante.isEmpty()) {
            restauranteRepository.save(new Restaurante(NOME_RESTAURANTE, ENDERECO_RESTAURANTE));
        }

        Cardapio cardapio;
        if (cardapioRepository.count() == 0) {
            cardapio = cardapioRepository.save(new Cardapio());
        } else {
            cardapio = cardapioRepository.findAll().get(0);
        }

        if (itemCardapioRepository.count() == 0) {
            List<ItemCardapio> itens = Arrays.asList(
                    new ItemCardapio(1L, "Moqueca de Palmito", 25.0, "comida", cardapio),
                    new ItemCardapio(2L, "Falafel Assado", 10.0, "comida", cardapio),
                    new ItemCardapio(3L, "Salada Primavera com Macarrao Konjac", 55.0, "comida", cardapio),
                    new ItemCardapio(4L, "Escondidinho de Inhame", 20.0, "comida", cardapio),
                    new ItemCardapio(5L, "Strogonoff de cogumelos", 32.5, "comida", cardapio),
                    new ItemCardapio(6L, "Cacarola de carne com legumes", 72.5, "comida", cardapio),
                    new ItemCardapio(7L, "Agua", 5.0, "bebida", cardapio),
                    new ItemCardapio(8L, "Suco", 8.0, "bebida", cardapio),
                    new ItemCardapio(9L, "Refrigerante", 8.0, "bebida", cardapio),
                    new ItemCardapio(10L, "Cerveja", 8.0, "bebida", cardapio),
                    new ItemCardapio(11L, "Vinho", 50.5, "bebida", cardapio));
            itemCardapioRepository.saveAll(itens);
        }
    }
}
