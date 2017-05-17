package Security;

import java.util.Random;
import org.luan.LUANObject;

public class SecuritySelector {
	private AES_CBC cbc=null;
	private AES_CTR ctr=null;
	private AES_ECB ecb =null;
	private AES_GCM gcm = null;
	private DES_CTR desCtr =null;
	private Random rand=null;
	public SecuritySelector(){
		this.cbc= new AES_CBC();
		this.ctr= new AES_CTR();
		this.ecb= new AES_ECB();
		this.gcm= new AES_GCM();
		this.desCtr=new DES_CTR();
		this.rand= new Random();
	}
	
	public byte[] encrypt(LUANObject luan) throws Exception{
		if(luan.getString("algorithm").contains("AES/CBC")){
			return cbc.encrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/CTR")){
			return ctr.encrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/ECB")){
			return ecb.encrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/GCM")){
			return gcm.encrypt(luan);
		}else if(luan.getString("algorithm").contains("DES/CTR")){
			return desCtr.encrypt(luan);
		}
		return null;
	}
	
	public byte[] decrypt(LUANObject luan) throws Exception{
		if(luan.getString("algorithm").contains("AES/CBC")){
			return cbc.decrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/CTR")){
			return ctr.decrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/ECB")){
			return ecb.decrypt(luan);
		}else if(luan.getString("algorithm").contains("AES/GCM")){
			return gcm.decrypt(luan);
		}else if(luan.getString("algorithm").contains("DES/CTR")){
			return desCtr.decrypt(luan);
		}
		return null;
	}

	public String generateKey(LUANObject luan) throws Exception{
		int bits=8;
		if(!luan.getString("algorithm").equals("DES/CTR/PKCS5Padding")){
			bits=(luan.getInteger("keySize")/8);
		}
		String allowed="qWE3[erFXCS!$w:Vi.Bt@uo/pa{<sR#YUI'O9_PjLK;JH+0G£df4D%]gkl*h|7zx¤2yA6&ZcN-M>T15?vb=nm,}Q8";
		char[] key= new char[bits];
		for(int i=0;i<bits;i++){
			key[i]=allowed.charAt(rand.nextInt(allowed.length()));
		}
		return new String(key);
	}
	
	public String generateIvKey(LUANObject luan) throws Exception{
		int bits=16;
		if(luan.getString("algorithm").equals("DES/CTR/PKCS5Padding")){
			bits=8;
		}
		String allowed="qWE3[erFXCS!$w:Vi.Bt@uo/pa{<sR#YUI'O9_PjLK;JH+0G£df4D%]gkl*h|7zx¤2yA6&ZcN-M>T15?vb=nm,}Q8";
		char[] key= new char[bits];
		for(int i=0;i<bits;i++){
			key[i]=allowed.charAt(rand.nextInt(allowed.length()));
		}
		return new String(key);
	}
	

	
	public int generatePwdIterations(){
		return new Random().nextInt(500000)+50000;
	}
}
	

