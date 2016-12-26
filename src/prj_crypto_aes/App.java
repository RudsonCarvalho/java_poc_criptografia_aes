/**
 * 
 */
package prj_crypto_aes;

import java.util.Random;

/**
 * @author Rudson Kiyoshi S. Carvalho
 * @data 2016-12-26
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String AES_KEY = "0123456789abcdef";
		
		String texto = "Olá Maria"; 
	
		System.out.println("-------------------------------------------");
		texto = Crypto.getInstance(AES_KEY).encrypt(texto);

		System.out.println("---------------**--------------------------");
		System.out.println("Texto criptografado com AES: ".concat(texto));
		
		System.out.println("-------------------------------------------");
		texto = Crypto.getInstance(AES_KEY).decrypt(texto);
		
		System.out.println("---------------**--------------------------");
		System.out.println("Texto decriptografado com AES: ".concat(texto));
		
		
		
	}

}
