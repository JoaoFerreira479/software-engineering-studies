package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Rendimento;
import com.universidade.aluguelcarros.persistence.RendimentoRepository;
import org.springframework.stereotype.Service;

@Service
public class RendimentoService extends AbstractCrudService<Rendimento> {

    public RendimentoService(RendimentoRepository repository) {
        super(repository, "Rendimento");
    }
}
