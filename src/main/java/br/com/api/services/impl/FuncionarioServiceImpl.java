package br.com.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entities.Funcionario;
import br.com.api.repositories.FuncionarioRepository;
import br.com.api.services.FuncionarioService;
/**
 * Classe que implementa os metodos de funcionarios
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistindo funcionario" + funcionario.toString());
		return this.funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando Funcionario por CPF: " + cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionario por E-mail: " + email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionario por ID: " + id);
		return Optional.ofNullable(this.funcionarioRepository.findOne(id));
	}

	
}
