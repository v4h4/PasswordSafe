package Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.luan.LUANObject;
 
public class AES_ECB {

	public byte[] encrypt(LUANObject luan){
		byte[] outputBytes=null;
		try{
			byte[] bFile=luan.toString().getBytes("ISO-8859-1");
			String key=luan.getString("key");
			String salt= luan.getString("salt");
			int keySize=luan.getInteger("keySize");
			int pwdIterations=luan.getInteger("pwdIterations");
			outputBytes = encryption(bFile,key,salt,keySize,pwdIterations);
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
		String salt= luan.getString("salt");
		int keySize=luan.getInteger("keySize");
		int pwdIterations=luan.getInteger("pwdIterations");
		return decryption(bFile,key,salt,keySize,pwdIterations);
	}
    
    
    private byte[] encryption(byte[] src, String password, String salt, int keySize, int pwdIterations){   
        byte[] encrypted=null;
    	try{
        	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecreKeySpec(password, salt,keySize,pwdIterations));
            encrypted = cipher.doFinal(src);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    	return encrypted;
    }
 
    private byte[] decryption(byte[] src, String password, String salt, int keySize, int pwdIterations){
    	byte[] decrypted=null;
    	try{
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getSecreKeySpec(password, salt,keySize,pwdIterations));
            decrypted = cipher.doFinal(src);
        }catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return decrypted;
    }
    
    private SecretKeySpec getSecreKeySpec(String password, String slt, int keySize, int pwdIterations){
    	SecretKeySpec secretKeySpec = null;
    	try{
    		byte[] salt = slt.getBytes("ISO-8859-1");
    	   	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),salt, pwdIterations, keySize);
            SecretKey secretKey = factory.generateSecret(spec);
            secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return secretKeySpec;
    }
}