package GUI_Main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.luan.LUANObject;
import SecureFileManager.FileManager;
import SecureFileManager.SingleInstanceApplicationManager;


public class MainFrameGui {
	
	private JFrame mainFrame = null;
	private PanelLoginGui loginPanelClass = null;
	private PanelCreateUserGui createNewUserClass = null; 
	private PanelMainGui mainPanelClass =null;
	private PanelDeleteUserGui deleteUserClass =null;
	private DialogGui dialog=null;
	private JMenuBarGui menu_bar = null;
	private FileManager fm = null;
	private boolean imageIconIsLocked=false;
	public MainFrameGui(){
		this.fm= new FileManager();
		createMainWindow();
		initlizeMenuBar();
	}
	
	public void openMainFrameWindow(){
		showLoginPanel();
		mainFrame.setVisible(true);
	}
	
	private void createMainWindow(){
		this.mainFrame= new JFrame();
		mainFrame.setTitle("PasswordSafe 1.0");
		mainFrame.setVisible(false);
		mainFrame.setSize(new Dimension(505, 355));
		mainFrame.setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setLocation(200, 10);
		this.dialog = new DialogGui(this.mainFrame);
		mainFrameCloseListener();
		addLoginPanelToMainFrame();
		addDeleteUSerToMainFrame();
		addCreateNewUserToMainFrame();
		addMainPanelToMainFrame();
	}
	
	private void initlizeMenuBar(){
		this.menu_bar = new JMenuBarGui(this, dialog);
		this.mainFrame.setJMenuBar(menu_bar.getJMenuBar());
	}
	
	
	private void addLoginPanelToMainFrame(){
		loginPanelClass = new PanelLoginGui(this, dialog);
		mainFrame.add(loginPanelClass.getJPanel()).setBounds(10, 10, 480, 305);
	}
	
	private void addCreateNewUserToMainFrame(){
		createNewUserClass = new PanelCreateUserGui(this,dialog);
		mainFrame.add(createNewUserClass.getJPanel()).setBounds(10, 10, 480, 365);
	}
	
	private void addMainPanelToMainFrame(){
		this.mainPanelClass = new PanelMainGui(dialog);
		mainFrame.add(mainPanelClass.getJPanel()).setBounds(10, 10, 1030, 620);
	}
	
	private void addDeleteUSerToMainFrame(){
		this.deleteUserClass = new PanelDeleteUserGui(dialog);
		mainFrame.add(deleteUserClass.getJPanel()).setBounds(10, 10, 480, 305);
	}
	
	private void mainFrameCloseListener(){
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				boolean close=dialog.dynamicConfirmationDialog("Closing PasswordSafe", "Are you sure that you want to close PasswordSafe?");
				if(close == true){
					new SingleInstanceApplicationManager().shutDownProcess();
					System.exit(0);
				}	
			}
		});
	}
	
	public void showLoginPanel(){
		setUnlockedIcon();
		menu_bar.setLoginPanelOptions();
		deleteUserClass.hideDeleteUserPanel();
		createNewUserClass.hideCreateNewUserPanel();
		mainPanelClass.hideMainPanel();
		loginPanelClass.showLoginPanel();
		mainFrame.setSize(new Dimension(505, 375));
	}
	
	public void showCreateNewUserPanel(){
		setLockedIcon();
		menu_bar.setCreatePanelOptions();
		loginPanelClass.hideLoginPanel();
		deleteUserClass.hideDeleteUserPanel();
		mainPanelClass.hideMainPanel();
		createNewUserClass.showCreateNewUserPanel();
		mainFrame.setSize(new Dimension(505, 435));
	}
		
	public void showDeleteUserPanel(){
		setLockedIcon();
		menu_bar.setDeletePanelOptions();
		loginPanelClass.hideLoginPanel();
		createNewUserClass.hideCreateNewUserPanel();
		mainPanelClass.hideMainPanel();
		deleteUserClass.showDeleteUserPanel();
		mainFrame.setSize(new Dimension(505, 375));
	}
	
	public void showMainPanel(LUANObject luan){
		setLockedIcon();
		try{
			menu_bar.setMainFrameLoggedinOptions(luan.getString("username"));
			loginPanelClass.hideLoginPanel();
			deleteUserClass.hideDeleteUserPanel();
			createNewUserClass.hideCreateNewUserPanel();
			mainPanelClass.showMainPanel(luan);
			mainFrame.setSize(new Dimension(1055, 690));
		}catch(Exception ex){
			ex.printStackTrace();
			dialog.dynamicErrorDialogWindow("Login Faild", "Login faild due wrong username and/or password/salt/Iv-Key");
			getLoginPanelSelectedAlgorithmMode();
		}
	}
	
	public String getLoginPanelSelectedAlgorithmMode(){
		return loginPanelClass.getSelectedAlgorithmMode();
	}
	
	public String getCreateNewUserSelectedAlgorithmMode(){
		return createNewUserClass.getSelectedAlgorithmMode();
	}
	
	public boolean isLoggedIn(){
		return mainPanelClass.isLoggedIn();
	}
	
	public void setLockedIcon(){
		mainFrame.setIconImage(fm.fileFromJar2Image("locked.png"));
		this.imageIconIsLocked=true;
	}
	
	public void setUnlockedIcon(){
		mainFrame.setIconImage(fm.fileFromJar2Image("unlocked.png"));
		this.imageIconIsLocked=false;
	}
	
	public boolean isImageIconLocked(){
		return this.imageIconIsLocked;
	}
}
