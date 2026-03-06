package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.domain.Usuario;
import com.universidade.aluguelcarros.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractCrudService<Usuario> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository, "Usuário");
    }
}
