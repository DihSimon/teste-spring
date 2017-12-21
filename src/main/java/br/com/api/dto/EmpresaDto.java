package br.com.api.dto;


/**
 * Dto referente a empresa
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public class EmpresaDto {

	private Long id;
	private String razaoSocial;
	private String cnpj;

	public EmpresaDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}

}
