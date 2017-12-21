package br.com.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe de teste do password Utils
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public class PasswordUtilsTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testeSenhaNulla() throws Exception{
		assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void testgerarHashSenha()  throws Exception{
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		assertTrue(bCryptPasswordEncoder.matches(SENHA, hash));
	}
}
