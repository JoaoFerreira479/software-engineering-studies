package com.universidade.matriculas.service;

import com.universidade.matriculas.model.Usuario;
import com.universidade.matriculas.repository.AlunoRepository;
import com.universidade.matriculas.repository.ProfessorRepository;
import com.universidade.matriculas.repository.SecretariaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UsuarioService {

	private final AlunoRepository alunoRepository;
	private final ProfessorRepository professorRepository;
	private final SecretariaRepository secretariaRepository;

	public UsuarioService(AlunoRepository alunoRepository, ProfessorRepository professorRepository,
			SecretariaRepository secretariaRepository) {
		this.alunoRepository = alunoRepository;
		this.professorRepository = professorRepository;
		this.secretariaRepository = secretariaRepository;
	}

	public Optional<Usuario> login(String email, String senha) {
		if (email == null || senha == null) {
			return Optional.empty();
		}
		Optional<? extends Usuario> usuarioEncontrado = Stream
				.of(alunoRepository.findByEmail(email), professorRepository.findByEmail(email),
						secretariaRepository.findByEmail(email))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findFirst();

		return usuarioEncontrado.filter(u -> senha.equals(u.getSenha())).map(Usuario.class::cast);
	}
}