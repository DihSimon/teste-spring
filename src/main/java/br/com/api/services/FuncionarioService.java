package br.com.api.services;

import java.util.Optional;

import br.com.api.entities.Funcionario;

/**
 * interface de funcionarios
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public interface FuncionarioService {

	/**
	 * gravar funcionario na base de dados
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Buscar Funcionario por CPF
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * Buscar funcionario por email
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Buscar funcionario por ID
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);
	
}
