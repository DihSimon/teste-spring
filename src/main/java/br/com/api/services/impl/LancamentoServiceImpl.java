package br.com.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.api.entities.Lancamento;
import br.com.api.repositories.LancamentoRepository;
import br.com.api.services.LancamentoService;

/**
 * Classe que implementa os servicos de lançamentos
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
@Service
public class LancamentoServiceImpl implements LancamentoService{
	
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long id, PageRequest pageRequest) {
		log.info("Buscando Funcionario por ID" + id + " e paginação");
		return this.lancamentoRepository.findByFuncionarioId(id, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando lancamento por ID" + id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo os lançamentos");
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo um Lançamento");
		this.lancamentoRepository.delete(id);
	}

}
