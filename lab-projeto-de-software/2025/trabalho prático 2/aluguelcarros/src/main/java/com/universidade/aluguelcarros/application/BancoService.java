package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Banco;
import com.universidade.aluguelcarros.persistence.BancoRepository;
import org.springframework.stereotype.Service;

@Service
public class BancoService extends AbstractCrudService<Banco> {

    public BancoService(BancoRepository repository) {
        super(repository, "Banco");
    }
}
