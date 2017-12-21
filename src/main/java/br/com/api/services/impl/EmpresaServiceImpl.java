package br.com.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.entities.Empresa;
import br.com.api.repositories.EmpresaRepository;
import br.com.api.services.EmpresaService;

/**
 * Classe que implementa os serviços de empresa
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	/**
	 * injeção dependencia do spring
	 */
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("buscando uma empresa para o CNPJ: "+ cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistingo empresa" + empresa.toString());
		return this.empresaRepository.save(empresa);
	}

}
