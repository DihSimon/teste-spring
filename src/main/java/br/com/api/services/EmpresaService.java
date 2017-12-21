package br.com.api.services;

import java.util.Optional;

import br.com.api.entities.Empresa;
/**
 * Empresa service
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public interface EmpresaService {
	
	/**
	 * Retorna uma empresa dado um CNPJ
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Persistir objeto empresa no banco de dados
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}
