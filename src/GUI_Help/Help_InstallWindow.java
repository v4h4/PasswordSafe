package GUI_Help;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import GUI_Main.DialogGui;
import SecureFileManager.FileChoosers;
import SecureFileManager.FileManager;

public class Help_InstallWindow {
	private JFrame installWindow = null;
	private DialogGui dialog=null;
	private FileManager fm = null;
	private Help_GuideInstalPanels guide = null;
	public Help_InstallWindow(DialogGui dialog){
		this.dialog=dialog;
		this.fm= new FileManager();
		createInstallWindow();
	}
	
	public void openInstallWindow(){
		installWindow.setVisible(true);
		//dialog.setMainFrameEnabled(false);
	}
	
	private void createInstallWindow(){
		this.installWindow= new JFrame();
		setLockedIcon();
		installWindow.setTitle("PasswordSafe 1.0");
		installWindow.setVisible(false);
		installWindow.setSize(new Dimension(605, 540));
		installWindow.setLayout(null);
		installWindow.setLocationRelativeTo(null);
		installWindow.setResizable(false);
		installWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		installWindow.setLocation(200, 10);
		this.dialog = new DialogGui(this.installWindow);
		installWindowCloseListener();
		this.guide = new Help_GuideInstalPanels(this,dialog);
		for(int i=0;i<guide.getJPanels().length;i++){
			installWindow.add(guide.getJPanels()[i]).setBounds(10, 10, 580, 495);
		}
		
	}
	
	private void installWindowCloseListener(){
		installWindow.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				boolean close=dialog.dynamicConfirmationDialog("Closing PasswordSafe", "Are you sure that you want to close the install Mode?");
				if(close == true){
					installWindow.setVisible(false);
				}	
			}
		});
	}
	
	private void setLockedIcon(){
		installWindow.setIconImage(fm.fileFromJar2Image("locked.png"));
	}
	
	public void hideInstallWindow(){
		this.installWindow.setVisible(false);
		FileChoosers.resetOld_local_policy();
		FileChoosers.resetOld_us_export_policy();
		FileChoosers.resetPath_to_target_local_policy();
		FileChoosers.resetPath_to_target_us_export_policy();
		//dialog.setMainFrameEnabled(true);
	}
}
