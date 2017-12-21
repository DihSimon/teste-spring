package br.com.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.api.entities.Funcionario;
import br.com.api.repositories.FuncionarioRepository;

/**
 * Classe de teste para testar um funcionario
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	private static final String CNPJ = "514636450001000";
	private static final String EMAIL = "teste@email.com.br";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findOne(Mockito.anyLong())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
	}
	
	@Test
	public void testePersistirFuncionario() throws Exception{
		Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());
		assertNotNull(funcionario);
	}
	
	@Test
	public void testebuscarFuncionarioPorId() throws Exception{
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testeBuscarFuncionarioPorEmail() throws Exception{
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail(EMAIL);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testeBuscarFuncionarioPorCpf() throws Exception{
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf(CNPJ);
		assertTrue(funcionario.isPresent());
	}
}
