package com.universidade.moedaestudantil.config.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.universidade.moedaestudantil.model.Usuario;
import com.universidade.moedaestudantil.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        String role = com.universidade.moedaestudantil.domain.TipoUsuario.from(user).name();
        return new org.springframework.security.core.userdetails.User(
          user.getEmail(),
          user.getSenha(),
          Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
      );
    }
}