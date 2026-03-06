package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Contrato;
import com.universidade.aluguelcarros.persistence.ContratoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoService extends AbstractCrudService<Contrato> {

    public ContratoService(ContratoRepository repository) {
        super(repository, "Contrato");
    }
}
