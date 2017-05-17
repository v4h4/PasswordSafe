package SecureFileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JFileChooser;
public class FileChoosers {
	private static byte[] unlimited_us_export_policy_byteArray=null;
	private static byte[] unlimited_local_policy_byteArray=null;
	private static String target_local_policy_path=null;
	private static String target_us_export_policy_path=null;
	private static byte[] old_local_policy=null;
	private static byte[] old_us_export_policy=null;
	
	
	public FileChoosers(){
		unlimitedStrengthSecurity_files_are_existing();	
	}
	
	private boolean unlimitedStrengthSecurity_files_are_existing(){
		unlimited_local_policy_byteArray = fileFromJar2byteArry("local_policy.jar");
		unlimited_us_export_policy_byteArray=fileFromJar2byteArry("US_export_policy.jar");
		if(unlimited_local_policy_byteArray!=null && unlimited_us_export_policy_byteArray!=null){
			return true;
		}
		return false;
	}
	
	private void copyFile(String copy_to_path) {
        try {
        	FileOutputStream fos = new FileOutputStream(copy_to_path);
        	if(copy_to_path.contains("local")){
        		fos.write(unlimited_local_policy_byteArray);
        	}else if(copy_to_path.contains("export")){
        		fos.write(unlimited_us_export_policy_byteArray);
        	}
        	fos.close();
        } catch (Exception ex) {
        	 ex.printStackTrace();
         }
      }
		
	public String savePathToFile(String title){
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save the Path to the file: "+title);
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String file_name=fileChooser.getSelectedFile().getAbsolutePath();
				if(title.contains("local_policy")){
					target_local_policy_path=file_name;
				}
				if(title.contains("export_policy")){
					target_us_export_policy_path=file_name;
				}
				return file_name;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static byte[] getByteArray_to_Unlimited_local_policy(){
		return unlimited_local_policy_byteArray;
	}
	
	public static byte[] getByteArray_to_Unlimited_us_export_policy(){
		return unlimited_us_export_policy_byteArray;
	}
	
	public static String getPath_to_target_local_policy(){
		return target_local_policy_path;
	}
	
	public static String getPath_to_target_us_export_policy(){
		return target_us_export_policy_path;
	}
	
	public static void resetPath_to_target_local_policy(){
		target_local_policy_path=null;
	}
	
	public static void resetPath_to_target_us_export_policy(){
		target_us_export_policy_path=null;
	}
	
	public static void resetOld_local_policy(){
		old_local_policy=null;
	}
	
	public static void resetOld_us_export_policy(){
		old_us_export_policy=null;
	}
	
	public static byte[] getOld_local_policy(){
		return old_local_policy;
	}
	
	public static byte[] getOld_us_export_policy(){
		return old_us_export_policy;
	}
	
	public void switch_local_policy_File(){
		File file = new File(target_local_policy_path);
		if(file.exists()){
			old_local_policy=filepath_2_byteArr(target_local_policy_path);
			file.delete();
		}
		copyFile(target_local_policy_path);
	}
	
	public void switch_us_export_policy_File(){
		File file = new File(target_us_export_policy_path);
		if(file.exists()){
			old_us_export_policy=filepath_2_byteArr(target_us_export_policy_path);
			file.delete();
		}
		copyFile(target_us_export_policy_path);
	}
	
	public byte[] filepath_2_byteArr(String path) {
        File file = new File(path);
        byte[] byteArray=null;
        if(file.exists()){
			byteArray = new byte[(int) file.length()];
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(byteArray);
				fileInputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
        return byteArray;
	}
	
	public boolean byteArrayIsIdentical(byte[] one,byte[] two){
		try{
			if(one.length==two.length){
				for(int i=0;i<one.length;i++){
					if(one[i]!=two[i]){
						return false;
					}
				}
				return true;
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	private byte[] fileFromJar2byteArry(String fileName){
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
	
	public void writeUnlimitedFileToApplicationFolder(String fileName){
		try{
			Path path = Paths.get(System.getProperty("user.dir"), fileName);
			FileOutputStream fileOuputStream = new FileOutputStream(path.toString()); 
		    if(fileName.contains("local_policy.jar")){
		    	fileOuputStream.write(unlimited_local_policy_byteArray);
		    }
		    else if(fileName.contains("US_export_policy.jar")){
		    	fileOuputStream.write(unlimited_us_export_policy_byteArray);
		    }
		    fileOuputStream.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public int getFontSizeForCorrectOP(int linux,int windows, int others ){
		String applicationsFolderPath=System.getProperty("user.dir");
		if(applicationsFolderPath.contains("/")){
			return linux;
		}
		else if(applicationsFolderPath.contains("\\")){
			return windows;
		}
		else{
			return others;
		}
	}
	
}
