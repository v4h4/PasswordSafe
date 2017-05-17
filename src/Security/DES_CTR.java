package Security;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.luan.LUANObject;

public class DES_CTR {
	
	
	public byte[] encrypt(LUANObject luan){
		byte[] outputBytes=null;
		try{
			byte[] bFile=luan.toString().getBytes("ISO-8859-1");
			String key=luan.getString("key");
			String ivKey=luan.getString("ivKey");
			String salt= luan.getString("salt");
			int pwdIterations=luan.getInteger("pwdIterations");
			outputBytes = encryption(bFile,key,salt,ivKey,pwdIterations);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return outputBytes;
	}

	public byte[] decrypt(LUANObject luan) throws Exception{
		byte[] bFile=null;
		try{
			bFile=luan.getByteArray("bfile");
		}catch(Exception ex){
			bFile=luan.toString().getBytes();
		}
		String key=luan.getString("key");
		String ivKey=luan.getString("ivKey");
		String salt= luan.getString("salt");
		int pwdIterations=luan.getInteger("pwdIterations");
		return decryption(bFile,key,salt,ivKey,pwdIterations);
	}
	
	private byte[] encryption(byte[] src, String key, String salt, String ivKey, int pwdIterations){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance("DES/CTR/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE,getSecreKeySpec(key, salt, pwdIterations), getSecretIvSpec(ivKey));
				byte [] cipherText= new byte[cipher.getOutputSize(src.length)];
				int ctLength=cipher.update(src,0,src.length,cipherText,0);
				ctLength+=cipher.doFinal(cipherText,ctLength);
				return cipherText;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}


	private byte[] decryption(byte[] src, String key, String salt, String ivKey, int pwdIterations){
		if(src!=null){
			try{
				Cipher cipher = Cipher.getInstance("DES/CTR/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE,getSecreKeySpec(key, salt, pwdIterations), getSecretIvSpec(ivKey));
				byte []ctLength= new byte[cipher.getOutputSize(src.length)];
				int qtLength=ctLength.length;
				byte [] plainText = new byte[cipher.getOutputSize(qtLength)]; 
				int ptLength = cipher.update(src,0,qtLength,plainText);
				ptLength+=cipher.doFinal(plainText,ptLength);
				return plainText; 
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	 private IvParameterSpec getSecretIvSpec(String iv_Key){
			IvParameterSpec secretIvSepc=null;
			try{
				byte[] ivKey = iv_Key.getBytes("ISO-8859-1");
				secretIvSepc = new IvParameterSpec(ivKey); 
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return secretIvSepc;
		}
	    
	    private SecretKeySpec getSecreKeySpec(String password, String slt, int pwdIterations){
	    	SecretKeySpec secretKeySpec = null;
	    	try{
	    		byte[] salt = slt.getBytes("ISO-8859-1");
	    	   	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),salt, pwdIterations, 64);
	            SecretKey secretKey = factory.generateSecret(spec);
	            secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "DES");
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    	return secretKeySpec;
	    }
}
