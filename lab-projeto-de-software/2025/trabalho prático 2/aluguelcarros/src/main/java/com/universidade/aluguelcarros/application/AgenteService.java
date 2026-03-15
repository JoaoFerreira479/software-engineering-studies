package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Agente;
import com.universidade.aluguelcarros.persistence.AgenteRepository;
import org.springframework.stereotype.Service;

@Service
public class AgenteService extends AbstractCrudService<Agente> {

    public AgenteService(AgenteRepository repository) {
        super(repository, "Agente");
    }
}
