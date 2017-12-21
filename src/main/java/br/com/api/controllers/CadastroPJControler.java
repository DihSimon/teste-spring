package br.com.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.dto.CadastroPJDTO;
import br.com.api.entities.Empresa;
import br.com.api.entities.Funcionario;
import br.com.api.enums.PerfilEnum;
import br.com.api.response.Response;
import br.com.api.services.EmpresaService;
import br.com.api.services.FuncionarioService;
import br.com.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJControler {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJControler.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public CadastroPJControler() {
		
	}
	/**
	 * 
	 * @param cadastroPJDTO
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPJDTO>> cadastrar(@Valid @RequestBody CadastroPJDTO cadastroPJDTO, BindingResult result) throws NoSuchAlgorithmException{
		log.info("Cadastrando PJ" + cadastroPJDTO.toString());
		Response<CadastroPJDTO> response = new Response<CadastroPJDTO>();
		
		this.validarDadosExistentes(cadastroPJDTO, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDTO);
		Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDTO, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando cadastro de PJ", result.hasErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPJDTO(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se a empresa ou funcionário já existe no banco de dados
	 * @param cadastroPJDTO
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPJDTO cadastroPJDTO, BindingResult result) {
		this.empresaService.buscarPorCnpj(cadastroPJDTO.getCnpj()).ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente")));
		this.funcionarioService.buscarPorCpf(cadastroPJDTO.getCpf()).ifPresent(emp -> result.addError(new ObjectError("funcionario", "Cpf já existente")));
		this.funcionarioService.buscarPorEmail(cadastroPJDTO.getEmail()).ifPresent(emp -> result.addError(new ObjectError("funcionario", "E-mail já existente")));
	}
	
	/**
	 * Metodo para converter DTO de cadastro para Entidade empresa
	 * @param  CadastroPJDTO dto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroPJDTO dto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(dto.getCnpj());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		return empresa;
	}
	
	/**
	 * Metodo para converter DTO de cadastra para uma entidade funcionario
	 * @param CadastroPJDTO dto, BindingResult result
	 * @return Funcionario funcionario
	 */
	private Funcionario converterDtoParaFuncionario(CadastroPJDTO dto, BindingResult result) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(dto.getNome());
		funcionario.setEmail(dto.getEmail());
		funcionario.setCpf(dto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(dto.getSenha()));
		return funcionario;
	}
	
	/**
	 * Popula DTO de cadastro com os dados do funcionario e da empresa
	 * @param funcionario
	 * @return cadastroPJDTO
	 */
	private CadastroPJDTO converterCadastroPJDTO(Funcionario funcionario) {
		CadastroPJDTO cadastroPJDTO = new CadastroPJDTO();
		cadastroPJDTO.setId(funcionario.getId());
		cadastroPJDTO.setNome(funcionario.getNome());
		cadastroPJDTO.setEmail(funcionario.getEmail());
		cadastroPJDTO.setCnpj(funcionario.getCpf());
		cadastroPJDTO.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJDTO.setCnpj(funcionario.getEmpresa().getCnpj());
		return cadastroPJDTO;
	}

}
