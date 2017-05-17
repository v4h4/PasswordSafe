package GUI_Main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.luan.LUANObject;
import GUI_tableLogics.JTableStructure;
import SecureFileManager.FileManager;

public class PanelMainGui {
	private FileManager fm=null;
	private JPanel mainPanel=null; 
	private DialogGui dialog = null;
	private boolean loggedIn=false;
	private JTableStructure ts=null;
	
	public PanelMainGui(DialogGui dialog){
		this.fm = new FileManager();
		this.dialog = dialog;
		initilizeMainPanel();
		initilizePasswordTable();
	}
	
	private void initilizeMainPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBorder(BorderFactory.createEtchedBorder());
		mainPanel.setVisible(false);
	}
	
	private void initilizePasswordTable(){
		this.ts = new JTableStructure(dialog, fm);
		this.mainPanel.add(ts.getPasswordTableScroll()).setBounds(10, 10, 1010, 600);
	}
	
	public JPanel getJPanel(){
		return this.mainPanel;
	}
	
	public void showMainPanel(LUANObject luan){
		ts.updateEntireTableFromFile(luan);
		mainPanel.setVisible(true);
		loggedIn=true;
	}
	
	public void hideMainPanel(){
		mainPanel.setVisible(false);
		ts.setLuanObectToNull();
		this.loggedIn=false;
	}
	
	public boolean isLoggedIn(){
		return this.loggedIn;
	}
	
}