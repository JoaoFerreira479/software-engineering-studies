package com.universidade.matriculas.repository;

import com.universidade.matriculas.model.Secretaria;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class SecretariaRepository extends ArquivoRepository<Secretaria> {

	public SecretariaRepository() {
		super("secretarias.dat");
	}

	public Optional<Secretaria> findByEmail(String email) {
		return findByCampo(Secretaria::getEmail, email);
	}
}