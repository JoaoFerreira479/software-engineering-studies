package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Emprego;
import com.universidade.aluguelcarros.persistence.EmpregoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpregoService extends AbstractCrudService<Emprego> {

    public EmpregoService(EmpregoRepository repository) {
        super(repository, "Emprego");
    }
}
