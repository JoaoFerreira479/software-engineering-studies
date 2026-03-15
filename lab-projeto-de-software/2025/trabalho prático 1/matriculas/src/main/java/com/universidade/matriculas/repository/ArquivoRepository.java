package com.universidade.matriculas.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import com.universidade.matriculas.exception.PersistenciaException;
import com.universidade.matriculas.model.Entidade;

public abstract class ArquivoRepository<T extends Entidade> {

	private final String nomeArquivo;
	private final Map<Long, T> cache = new ConcurrentHashMap<>();
	private final AtomicLong sequence;

	protected ArquivoRepository(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		carregarDoArquivo();
		long maxId = cache.keySet().stream().max(Long::compare).orElse(0L);
		this.sequence = new AtomicLong(maxId);
	}

	public T salvar(T entidade) {
		if (entidade.getId() == null) {
			entidade.setId(sequence.incrementAndGet());
		}
		cache.put(entidade.getId(), entidade);
		salvarNoArquivo();
		return entidade;
	}

	public Optional<T> buscarPorId(Long id) {
		return Optional.ofNullable(cache.get(id));
	}

	public List<T> listarTodos() {
		return new ArrayList<>(cache.values());
	}

	public void deletarPorId(Long id) {
		cache.remove(id);
		salvarNoArquivo();
	}

	protected Optional<T> findByCampo(Function<T, String> getter, String valor) {
		if (valor == null) {
			return Optional.empty();
		}
		String valorNormalizado = valor.trim();
		return listarTodos().stream()
				.filter(e -> {
					String campo = getter.apply(e);
					return campo != null && campo.equalsIgnoreCase(valorNormalizado);
				})
				.findFirst();
	}

	@SuppressWarnings("unchecked")
	private void carregarDoArquivo() {
		File file = new File(nomeArquivo);
		if (!file.exists()) {
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			Map<Long, T> dados = (Map<Long, T>) ois.readObject();
			cache.putAll(dados);
		} catch (IOException | ClassNotFoundException e) {
			throw new PersistenciaException(
					"Erro ao carregar dados do arquivo: " + nomeArquivo + " - " + e.getMessage(), e);
		}
	}

	private synchronized void salvarNoArquivo() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
			oos.writeObject(cache);
		} catch (IOException e) {
			throw new PersistenciaException(
					"Erro ao salvar dados no arquivo: " + nomeArquivo + " - " + e.getMessage(), e);
		}
	}
}
