package GUI_Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogGui {
	private JFrame mainFrame = null;
	
	public DialogGui(JFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	@SuppressWarnings("static-access")
	public boolean dynamicWarningDialogWindow(String title,String message){
		mainFrame.setEnabled(false);
		JOptionPane errorDialog= new JOptionPane();
		errorDialog.showMessageDialog(null,message,title,JOptionPane.WARNING_MESSAGE);
		while(true){
			if(errorDialog.isShowing()==false){
				mainFrame.setEnabled(true);
				return true;
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public boolean dynamicErrorDialogWindow(String title,String message){
		//mainFrame.setEnabled(false);
		JOptionPane errorDialog= new JOptionPane();
		errorDialog.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
		while(true){
			if(errorDialog.isShowing()==false){
				//mainFrame.setEnabled(true);
				return true;
			}
		}	
	}
	
	public boolean dynamicConfirmationDialog(String title,String message){
		mainFrame.setEnabled(false);
		int dialogResult = JOptionPane.showConfirmDialog(null, message, title,JOptionPane.YES_NO_OPTION);
		while(true){
			if (dialogResult == JOptionPane.YES_OPTION) {
				mainFrame.setEnabled(true);
				return true;
			}else{
				mainFrame.setEnabled(true);
				return false;
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public boolean SingleInstanceErrorDialogWindow(){
		JOptionPane errorDialog= new JOptionPane();
		errorDialog.showMessageDialog(null,"An Instance of this application is allready running","Single Instance Error",JOptionPane.WARNING_MESSAGE);
		while(true){
			if(errorDialog.isShowing()==false){
				System.exit(0);
			}
		}
	}
	
	
	public void setMainFrameVisble(boolean action){
		this.mainFrame.setVisible(action);
	}
	
	public void setMainFrameEnabled(boolean action){
		this.mainFrame.setEnabled(action);
	}

}
