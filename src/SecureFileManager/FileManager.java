package SecureFileManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import org.luan.LUANObject;

import Security.SecuritySelector;

public class FileManager {
	private SecuritySelector sec = null;
	private String usersFolderPath = null;
	private String applicationsFolderPath = null;
	private String passwordSafe_local_policy_Path = null;
	private String passwordSafe_US_export_policy_Path = null;
	private String java_local_policy_Path = "";
	private String java_US_export_policy_Path = "";

	public FileManager() {
		this.sec = new SecuritySelector();
		createUserFolderIfNotExists();
		createPathToJavaSecurityUnlimitedStrengthFiles();
	}

	private void createUserFolderIfNotExists() {
		File userFolder = null;
		applicationsFolderPath = System.getProperty("user.dir");// new
																// File("test").getAbsolutePath();
		Path path = Paths.get(applicationsFolderPath, "Users");
		if (!Files.exists(path) /* && f.isDirectory() */) {
			userFolder = new File(applicationsFolderPath, "Users");
			userFolder.mkdir();
			usersFolderPath = userFolder.getAbsolutePath();
		} else {
			executeTerminalCmd("locate PasswordSafe.jar");
			
			
			userFolder = new File(applicationsFolderPath, "Users");
			usersFolderPath = userFolder.getAbsolutePath();
		}
	}

	private void createPathToJavaSecurityUnlimitedStrengthFiles() {
		String securityFolderPath = Paths.get(applicationsFolderPath, "UnlimitedStrengthSecurity").toString();
		this.passwordSafe_local_policy_Path = Paths.get(securityFolderPath, "local_policy").toString();
		this.passwordSafe_US_export_policy_Path = Paths.get(securityFolderPath, "US_export_policy").toString();
	}

	private byte[] convertFileToByteArray_IO(File file) {
		FileInputStream fileInputStream = null;
		byte[] byteFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(byteFile);
			fileInputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return byteFile;
	}

	private File getUsernameFolder(String username) {
		File[] files = new File(usersFolderPath).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().equals(username)) {
				return files[i];
			}
		}
		return null;
	}

	private File getPasswordTableFile(String username) {
		File[] files = new File(usersFolderPath).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().equals(username)) {
				File[] usernameFolder = files[i].listFiles();
				for (int q = 0; q < usernameFolder.length; q++) {
					if (usernameFolder[q].getName().equals("passwordTable.txt")) {
						return usernameFolder[q];
					}
				}
			}
		}
		return null;
	}

	private File getPasswordFile(String username) {
		File[] files = new File(usersFolderPath).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().equals(username)) {
				File[] usernameFolder = files[i].listFiles();
				for (int q = 0; q < usernameFolder.length; q++) {
					if (usernameFolder[q].getName().equals("password.txt")) {
						return usernameFolder[q];
					}
				}
			}
		}
		return null;
	}

	private File[] getFilesFromUsers() {
		try {
			File[] files = new File(usersFolderPath).listFiles();
			return files;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[] getUsers() throws Exception {
		File[] fileNames = getFilesFromUsers();
		int arrSize;
		if (fileNames == null) {
			arrSize = 1;
		} else {
			arrSize = fileNames.length + 1;
		}
		String[] users = new String[arrSize];
		for (int i = 0; i < users.length; i++) {
			if (i == 0) {
				users[i] = "Select User";
			} else if (i > 0) {
				users[i] = fileNames[i - 1].getName();
			}
		}
		return users;
	}

	public boolean isUsernameUnique(String username) {
		File[] file = getFilesFromUsers();
		for (int i = 0; i < file.length; i++) {
			if (file[i].getName().equals(username)) {
				return false;
			}
		}
		return true;
	}

	public void createNewUsername(LUANObject luan) {
		try {
			File usernameFolder = new File(usersFolderPath, luan.getString("username").toLowerCase());
			usernameFolder.mkdir();
			luan.put("passwordTable_Key", sec.generateKey(luan));
			luan.put("passwordTable_Iv-Key", sec.generateIvKey(luan));
			luan.put("passwordTable_Salt", sec.generateKey(luan));
			luan.put("passwordTable_PwdIterations", sec.generatePwdIterations());
			luan.put("passwordTable_KeySize", luan.getInteger("keySize"));
			new File(usernameFolder.getAbsolutePath(), "password.txt").createNewFile();
			updatePasswordFile(luan);
			if (luan.containsString("salt")) {
				luan.removeString("salt");
			}
			if (luan.containsString("ivKey")) {
				luan.removeString("ivKey");
			}
			if (luan.containsString("key")) {
				luan.removeString("key");
			}
			if (luan.containsInteger("pwdIterations")) {
				luan.removeInteger("pwdIterations");
			}
			if (luan.containsInteger("keySize")) {
				luan.removeInteger("keySize");
			}
			luan.put("key", luan.getString("passwordTable_Key"));
			luan.put("ivKey", luan.getString("passwordTable_Iv-Key"));
			luan.put("salt", luan.getString("passwordTable_Salt"));
			luan.put("keySize", luan.getInteger("passwordTable_KeySize"));
			luan.put("pwdIterations", luan.getInteger("passwordTable_PwdIterations"));
			if (luan.containsString("passwordTable_Key")) {
				luan.removeString("passwordTable_Key");
			}
			if (luan.containsString("passwordTable_Iv-Key")) {
				luan.removeString("passwordTable_Iv-Key");
			}
			if (luan.containsString("passwordTable_Salt")) {
				luan.removeString("passwordTable_Salt");
			}
			if (luan.containsInteger("passwordTable_KeySize")) {
				luan.removeInteger("passwordTable_KeySize");
			}
			if (luan.containsInteger("passwordTable_PwdIterations")) {
				luan.removeInteger("passwordTable_PwdIterations");
			}
			new File(usernameFolder.getAbsolutePath(), "passwordTable.txt").createNewFile();
			updatePasswordTable(luan);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isfileExcisting(String username) {
		Path usernamePath = Paths.get(usersFolderPath, username);
		File unameMap = new File(usernamePath.toString());
		Path userPasswordPath = Paths.get(usernamePath.toString(), "password.txt");
		File password = new File(userPasswordPath.toString());
		Path userPasswordTablePath = Paths.get(usernamePath.toString(), "passwordTable.txt");
		File passwordTable = new File(userPasswordTablePath.toString());
		if (unameMap.exists() && password.exists() && passwordTable.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteuser(LUANObject luan) throws Exception {
		File file = getUsernameFolder(luan.getString("username"));
		if (file != null && file.isDirectory() == true) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
			file.delete();
			return true;
		} else if (file != null && file.isDirectory() == false) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	public LUANObject readEncryptedPassword(LUANObject luan) throws Exception {
		File file = getPasswordFile(luan.getString("username"));
		luan.put("bfile", convertFileToByteArray_IO(file));
		byte[] decryptedByteArrFile = sec.decrypt(luan);
		String content = new String(decryptedByteArrFile, "ISO-8859-1");
		return new LUANObject(content);
	}

	public LUANObject readEncryptedPasswordTable(LUANObject luan) throws Exception {
		File file = getPasswordTableFile(luan.getString("username"));
		luan.put("bfile", convertFileToByteArray_IO(file));
		byte[] decryptedByteArrFile = sec.decrypt(luan);
		String content = new String(decryptedByteArrFile, "ISO-8859-1");
		return new LUANObject(content);
	}

	public void updatePasswordTable(LUANObject luan) throws Exception {
		byte[] bfile = sec.encrypt(luan);
		File file = getPasswordTableFile(luan.getString("username"));
		FileOutputStream fileOuputStream = new FileOutputStream(file.getAbsolutePath());
		fileOuputStream.write(bfile);
		fileOuputStream.close();
	}

	public void updatePasswordFile(LUANObject luan) throws Exception {
		byte[] bfile = sec.encrypt(luan);
		File file = getPasswordFile(luan.getString("username"));
		FileOutputStream fileOuputStream = new FileOutputStream(file.getAbsolutePath());
		fileOuputStream.write(bfile);
		fileOuputStream.close();
	}

	/*public String getLockedImagePath() {
		Path picturesPath = Paths.get(applicationsFolderPath, "Pictures");
		Path lockedPath = Paths.get(picturesPath.toString(), "locked.png");
		if (!Files.exists(picturesPath) /* && f.isDirectory() ) {
			File picFolder = new File(picturesPath.toString());
			picFolder.mkdir();
		}
		return lockedPath.toString();
	}

	public String getUnlockedImagePath() {
		Path picturesPath = Paths.get(applicationsFolderPath, "Pictures");
		Path unlockedPath = Paths.get(picturesPath.toString(), "unlocked.png");

		if (!Files.exists(picturesPath) /* && f.isDirectory() ) {
			File picFolder = new File(picturesPath.toString());
			picFolder.mkdir();
		}
		return unlockedPath.toString();
	}*/

	/***********************
	 * Getters and Setters
	 **********************************/
	public int getFontSizeForCorrectOP(int linux,int windows, int others ){
		if(System.getProperty("user.dir").contains("/")){
			return linux;
		}
		else if(System.getProperty("user.dir").contains("\\")){
			return windows;
		}
		else{
			return others;
		}
	}
	
	public boolean os_is_windows(){
		if(System.getProperty("user.dir").contains("/")){
			return false;
		}
		else if(System.getProperty("user.dir").contains("\\")){
			return true;
		}
		return false;
	}
	
	public String getApplicationsFolderPath() {
		return this.applicationsFolderPath;
	}

	public String getPasswordSafe_local_policy_Path() {
		return this.passwordSafe_local_policy_Path;
	}

	public String getPasswordSafe_US_export_policy_Path() {
		return this.passwordSafe_US_export_policy_Path;
	}

	public String getJava_US_export_policy_Path() {
		return this.java_US_export_policy_Path;
	}

	public void setJava_US_export_policy_Path(String path) {
		this.java_US_export_policy_Path = path;
	}

	public String getJava_local_policy_Path() {
		return this.java_local_policy_Path;
	}

	public void setJava_local_policy_Path(String path) {
		this.java_local_policy_Path = path;
	}
	
	public ImageIcon getImageIcon(String path_string,int width,int height){
		Path path = Paths.get(applicationsFolderPath, "instructionImages");
		path = Paths.get(path.toString(), path_string);
		ImageIcon instruction_image=null;
		try {
			//File file = new File(path.toString());
			//Image image = ImageIO.read(file);
			Image image = ImageIO.read(fileFromJar2ImageInputStream(path_string));
			BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(image, 0, 0, width, height, null);
		    g2.dispose();
			image =resizedImg;
			instruction_image = new ImageIcon(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return instruction_image;
	}
	
	public Image getResizedImage(Image srcImg, int width, int height){
	   // Image image = new Imag
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, width, height, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	public byte[] fileFromJar2byteArry(String fileName){
		byte[] byteArray=null;
		try{
			String dir =System.getProperty("user.dir");
			Path path_zip = Paths.get(dir, "PasswordSafe.jar");
			ZipFile zf = new ZipFile(path_zip.toFile());
			try {
				for (Enumeration<? extends ZipEntry> e = zf.entries();e.hasMoreElements();) {
					ZipEntry ze = e.nextElement();
					String name = ze.getName();
					if(name.endsWith(fileName)){
						InputStream in = zf.getInputStream(ze);
						ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
						int reads = in.read(); 
						while(reads != -1){ 
							baos.write(reads); 
							reads = in.read(); 
						} 
						byteArray= baos.toByteArray();
						zf.close();
						return byteArray;
					}
				}
			}finally {
			  zf.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return null;
	}
	
	public ImageInputStream fileFromJar2ImageInputStream(String fileName){
		ImageInputStream iis=null;
		try{
			String dir =System.getProperty("user.dir");
			Path path_zip = Paths.get(dir, "PasswordSafe.jar");
			ZipFile zf = new ZipFile(path_zip.toFile());
			try {
				for (Enumeration<? extends ZipEntry> e = zf.entries();e.hasMoreElements();) {
					ZipEntry ze = e.nextElement();
					String name = ze.getName();
					if(name.endsWith(fileName)){
						InputStream in = zf.getInputStream(ze);
						ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
						int reads = in.read(); 
						while(reads != -1){ 
							baos.write(reads); 
							reads = in.read(); 
						} 
						byte[] test= baos.toByteArray();
						ByteArrayInputStream bais = new ByteArrayInputStream(test);
				        iis = ImageIO.createImageInputStream(bais);
					}
				}
			} finally {
			  zf.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return iis;
	}
	
	public BufferedImage fileFromJar2Image(String fileName){
		BufferedImage bufferedImage=null;
		try{
			String dir =System.getProperty("user.dir");
			Path path_zip = Paths.get(dir, "PasswordSafe.jar");
			ZipFile zf = new ZipFile(path_zip.toFile());
			try {
				for (Enumeration<? extends ZipEntry> e = zf.entries();e.hasMoreElements();) {
					ZipEntry ze = e.nextElement();
					String name = ze.getName();
					if(name.endsWith(fileName)){
						InputStream in = zf.getInputStream(ze);
						ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
						int reads = in.read(); 
						while(reads != -1){ 
							baos.write(reads); 
							reads = in.read(); 
						} 
						InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
				        bufferedImage = ImageIO.read(inputStream);
					}
				}
			} finally {
			  zf.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return bufferedImage;
	}
	
	public String executeTerminalCmd(String cmd){
		String result=null;
		StringBuffer output = new StringBuffer();
		Process process;
		try{
			process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line="";
			while((line=reader.readLine())!=null){
				output.append(line+"\n");
			}
			result=output.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
