package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Empresa;
import com.universidade.aluguelcarros.persistence.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends AbstractCrudService<Empresa> {

    public EmpresaService(EmpresaRepository repository) {
        super(repository, "Empresa");
    }
}
