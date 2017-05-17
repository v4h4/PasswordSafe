package SecureFileManager;

import java.io.File;
import java.nio.file.Paths;

import GUI_Main.DialogGui;

public class SingleInstanceApplicationManager {
	
	private File folder=null;
	private File file=null;
    private String single_Instance_folder=null;
    private String single_Instance_file=null;
    private DialogGui dialog = null;
    public SingleInstanceApplicationManager() {
    	this.dialog = new DialogGui(null);
    	single_Instance_folder = Paths.get(System.getProperty("user.dir"),"single_Instance").toString();
    	single_Instance_file = Paths.get(single_Instance_folder,"PasswordSafe.lock").toString();
    	this.folder = new File(single_Instance_folder);
    	if (!folder.exists()) {
            folder.mkdir();
        }
    }
	
	public void shutDownProcess(){
		file = new File(single_Instance_file);
		if (file.exists()) {
        	try{
        		 file.delete();
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        }
	}
	
	public void startUpProcess(){
		file = new File(single_Instance_file);
        if (!file.exists()) {
        	try{
        		file.createNewFile();
        	}catch(Exception ex){
        		ex.printStackTrace();
        		dialog.SingleInstanceErrorDialogWindow();
        	}
        }
        else{
        	dialog.SingleInstanceErrorDialogWindow();
        }
	}
}
