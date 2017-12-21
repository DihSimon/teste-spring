package br.com.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe utilitaria para gerar senhas encriptografadas
 * @author <a href="mailto:diegosimoncarmona@gmail.com">Diego Simon</a>
 */
public class PasswordUtils {

	
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {
		
	}
	
	/**
	 * Gerar um hash utilizando o Bcrypt
	 * @param senha
	 * @return senha Cruptografada
	 */
	public static String gerarBCrypt(String senha) {
		if(senha == null) {
			return senha;
		}
		
		log.info("Gerando senhga hash com o Bcrypt.");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(senha);
	}
}
