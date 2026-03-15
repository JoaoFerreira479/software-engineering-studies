package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Cliente;
import com.universidade.aluguelcarros.persistence.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractCrudService<Cliente> {

    public ClienteService(ClienteRepository repository) {
        super(repository, "Cliente");
    }
}
