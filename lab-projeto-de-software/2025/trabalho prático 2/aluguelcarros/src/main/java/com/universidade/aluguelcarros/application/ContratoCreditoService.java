package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.ContratoCredito;
import com.universidade.aluguelcarros.persistence.ContratoCreditoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoCreditoService extends AbstractCrudService<ContratoCredito> {

    public ContratoCreditoService(ContratoCreditoRepository repository) {
        super(repository, "Contrato de Crédito");
    }
}
