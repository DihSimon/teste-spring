package br.com.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.api.entities.Lancamento;

/**
 * Interface De lançamentos
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public interface LancamentoService {

	/**
	 * Retorna uma lista paginada de um determinado funcionario
	 * @param id
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long id, PageRequest pageRequest);
	
	/**
	 * Retorna um lancamento por id
	 * @param id
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * persistir um lançamento
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Remover um lancamento por ID
	 * @param id
	 */
	void remover(Long id);
}
