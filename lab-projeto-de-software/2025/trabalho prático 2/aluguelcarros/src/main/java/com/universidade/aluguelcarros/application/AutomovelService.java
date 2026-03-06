package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Automovel;
import com.universidade.aluguelcarros.persistence.AutomovelRepository;
import org.springframework.stereotype.Service;

@Service
public class AutomovelService extends AbstractCrudService<Automovel> {

    public AutomovelService(AutomovelRepository repository) {
        super(repository, "Automóvel");
    }
}
