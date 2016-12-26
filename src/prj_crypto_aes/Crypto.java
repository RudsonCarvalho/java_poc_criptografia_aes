/**
 * 
 */
package prj_crypto_aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Rudson Kiyoshi S. Carvalho
 * @data 2016-12-26
 */
public class Crypto {
	
	  private final static String TAG_LOG = "[Crypto]";

	    private Crypto() {}

	    public static Crypto getInstance(final String key) {

	        if (crypto == null) {
	        	crypto = new Crypto();
	        }

	        crypto.key = key;
	        
	        return crypto;
	    }

	    private String key;
	    
	    //SecretKeySpec ALGORITH
	    private final String ALGORITHM = "AES";

	    //VARIABLES AES
	    private final String SKI_ALG = "AES/CBC/PKCS5Padding";
	    private final String UTF_8 = "UTF-8";

	    private static Crypto crypto;

	    private static IvParameterSpec spec;
	    static {
	        byte[] bytes = new byte[16];
	        Arrays.fill(bytes, (byte) 0);
	        spec = new IvParameterSpec(bytes);
	    }

	    private SecretKeySpec getSecretKey() throws  UnsupportedEncodingException{

	        if (key == null){
	            throw new ExceptionInInitializerError("Erro nao foi inicializado uma chave valida.");
	        }

	        return new SecretKeySpec(key.getBytes(UTF_8), ALGORITHM);
	    }

	    /**
	     * Criptografa um texto
	     * @param textEncryption
	     * @return
	     */
	    public String encrypt(final String textEncryption){

	        Log.i(TAG_LOG, "Security => \"encrypt\" text in:  ".concat(textEncryption));

	        String textEncrypted = null;

	        if (textEncryption != null) {
	            try {

	                Cipher cipher = Cipher.getInstance(SKI_ALG);

	                cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), spec);

	                byte[] encryptedBytes = cipher.doFinal(textEncryption.getBytes(UTF_8));

	                textEncrypted = Base64.getEncoder().encodeToString(encryptedBytes);

	            } catch (NoSuchAlgorithmException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "NoSuchAlgorithmException ".concat(e.getMessage()));
	            } catch (NoSuchPaddingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "NoSuchPaddingException ".concat(e.getMessage()));
	            } catch (InvalidKeyException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "InvalidKeyException ".concat(e.getMessage()));
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "UnsupportedEncodingException ".concat(e.getMessage()));
	            } catch (InvalidAlgorithmParameterException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "InvalidAlgorithmParameterException ".concat(e.getMessage()));
	            } catch (BadPaddingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "BadPaddingException ".concat(e.getMessage()));
	            } catch (IllegalBlockSizeException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "IllegalBlockSizeException ".concat(e.getMessage()));
	            }
	        }

	        Log.i(TAG_LOG, "Security => \"encrypt\" text out:  ".concat(textEncrypted));

	        return textEncrypted;
	    }

	    /**
	     * Decriptografa um texto
	     * @param textDecryption
	     * @return
	     */
	    public String decrypt(final String textDecryption){

	        Log.i(TAG_LOG, "Security => \"decrypt\" text in:  ".concat(textDecryption));

	        String textDecrypted = null;

	        if (textDecryption != null) {
	            try {

	                Cipher cipher = Cipher.getInstance(SKI_ALG);

	                cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec);

	                byte[] encryptedBytes = cipher.doFinal(Base64.getDecoder().decode(textDecryption.getBytes(UTF_8)));
	                
	                textDecrypted = new String(encryptedBytes);

	            } catch (NoSuchAlgorithmException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "NoSuchAlgorithmException ".concat(e.getMessage()));
	            } catch (NoSuchPaddingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "NoSuchPaddingException ".concat(e.getMessage()));
	            } catch (InvalidKeyException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "InvalidKeyException ".concat(e.getMessage()));
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "UnsupportedEncodingException ".concat(e.getMessage()));
	            } catch (InvalidAlgorithmParameterException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "InvalidAlgorithmParameterException ".concat(e.getMessage()));
	            } catch (BadPaddingException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "BadPaddingException ".concat(e.getMessage()));
	            } catch (IllegalBlockSizeException e) {
	                e.printStackTrace(); //TODO: remover
	                Log.e(TAG_LOG, "IllegalBlockSizeException ".concat(e.getMessage()));
	            }
	        }

	        Log.i(TAG_LOG, "Security => \"decrypt\" text out:  ".concat(textDecrypted));

	        return textDecrypted;
	    }
	    
	    
	    /***
	     * Implementacao de um log qualquer
	     * @author Rudson Kiyoshi S. Carvalho - IBM
	     * @data 2016-12-26
	     */
	   public static class Log {
		   public static void i(String tag, String message) {
			   System.out.println("Info: " + tag + ": " + message);
		   }
		   public static void e(String tag, String message) {
			   System.out.println("Erro: " + tag + ": " + message);
		   }
	   }
}
