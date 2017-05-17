package GUI_Main;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GUI_Help.About_PasswordSafe;
import GUI_Help.Help_InstallWindow;
import GUI_Help.InstructionsWindow;

public class JMenuBarGui {
	private DialogGui dialog=null;
	private MainFrameGui mainFrameGui = null;
	private JMenuBar menu_bar = null;
	private JMenu accountManager_menu=null;;
	private JMenuItem login_menuItem=null;
	private JMenuItem logout_menuItem=null;
	private JMenuItem createUser_menuItem=null;
	private JMenuItem deleteUser_menuItem=null;
	private JMenu help_menu;
	private JMenuItem instructions_menuItem;
	private JMenuItem about_menuItem;
	private JMenuItem install256Stregth;
	private Help_InstallWindow install256 =null;
	private InstructionsWindow instructions=null;
	private JLabel userLabel= null;
	private About_PasswordSafe about_ps = null;
	public JMenuBarGui(MainFrameGui mainFrameGui,DialogGui dialog){
		this.mainFrameGui = mainFrameGui;
		this.dialog=dialog;
		initilizeAccoutmanager_menu();
		initilizeHelp_menu();
		initilizeUserLabelToMenuBar();
		initilizeMenuBar();
		startLogin_menuItemEventListener();
		startCreateUser_menuItemEventListener();
		startDeleteUser_menuItemEventListener();
		startLogout_menuItemEventListener();
		startAbout_menuItemEventListener();
		startInstructions_menuItemEventListener();
		startInstall256Strength_menuItemEventListener();
		this.install256 = new Help_InstallWindow(dialog);
		this.instructions = new InstructionsWindow();
		this.about_ps = new About_PasswordSafe();
	}
	
	private void initilizeMenuBar(){
		this.menu_bar = new JMenuBar();
		this.menu_bar.add(accountManager_menu);
		this.menu_bar.add(help_menu);
		this.menu_bar.add(Box.createGlue());
		this.menu_bar.add(userLabel);
	}
	
	private void initilizeAccoutmanager_menu(){
		this.accountManager_menu = new JMenu("Account Manager");
		this.accountManager_menu.setVisible(true);
		this.accountManager_menu.setLayout(new FlowLayout());
		this.login_menuItem = new JMenuItem("Login");
		this.createUser_menuItem = new JMenuItem("Create New User");
		this.deleteUser_menuItem = new JMenuItem("Delete An User");
		this.logout_menuItem = new JMenuItem("Logout");
		this.accountManager_menu.add(login_menuItem);
		this.accountManager_menu.add(createUser_menuItem);
		this.accountManager_menu.add(deleteUser_menuItem);
		this.accountManager_menu.add(logout_menuItem);
	}
	
	private void initilizeHelp_menu(){
		this.help_menu = new JMenu("Help");
		this.help_menu.setVisible(true);//LOOK HERE
		this.help_menu.setLayout(new FlowLayout());
		this.about_menuItem = new JMenuItem("About PasswordSafe");
		this.instructions_menuItem = new JMenuItem("Instructions");
		this.install256Stregth = new JMenuItem("Install 256-bit key-size");
		this.help_menu.add(about_menuItem);
		this.help_menu.add(instructions_menuItem);
		this.help_menu.add(install256Stregth);
	}
	
	private void initilizeUserLabelToMenuBar(){
		this.userLabel = new JLabel();
		this.userLabel.setVisible(false);
	}
		
	public JMenuBar getJMenuBar(){
		return this.menu_bar;
	}
	
	private void startLogin_menuItemEventListener(){
		class login_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				mainFrameGui.showLoginPanel();
			}
		}
		login_menuItem.addActionListener(new login_menuItem());
	}
	
	private void startCreateUser_menuItemEventListener(){
		class createUser_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				mainFrameGui.showCreateNewUserPanel();
			}
		}
		createUser_menuItem.addActionListener(new createUser_menuItem());
	}
	
	private void startDeleteUser_menuItemEventListener(){
		class deleteUser_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				mainFrameGui.showDeleteUserPanel();
			}
		}
		deleteUser_menuItem.addActionListener(new deleteUser_menuItem());
	}
	
	private void startLogout_menuItemEventListener(){
		class logout_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				boolean logout=dialog.dynamicConfirmationDialog("Log out", "Are you sure that you want to logout?");
				if(logout==true){
					mainFrameGui.showLoginPanel();
				}
			}
		}
		logout_menuItem.addActionListener(new logout_menuItem());
	}
	
	private void startAbout_menuItemEventListener(){
		class about_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				about_ps.set_About_Window_Visible();
			}
		}
		about_menuItem.addActionListener(new about_menuItem());
	}
	
	private void startInstructions_menuItemEventListener(){
		class instructions_menuItem implements ActionListener{
			public void actionPerformed(ActionEvent e){
				instructions.showInstructions();
			}
		}
		instructions_menuItem.addActionListener(new instructions_menuItem());
	}
	
	private void startInstall256Strength_menuItemEventListener(){
		class install256Stregth implements ActionListener{
			public void actionPerformed(ActionEvent e){
				install256.openInstallWindow();
			}
		}
		install256Stregth.addActionListener(new install256Stregth());
	}
	
	
	private void setUserLabel(String userLabel){
		this.userLabel.setText("Logged in as: "+userLabel+"  ");
		this.userLabel.setBorder(BorderFactory.createEtchedBorder());
		this.userLabel.setVisible(true);
	}
	
	private void removeUserLabel(){
		this.userLabel.setText("");
		this.userLabel.setBorder(BorderFactory.createEtchedBorder());
		this.userLabel.setVisible(false);
	}
	
	public void setMainFrameLoggedinOptions(String username){
		try{
			setUserLabel(username);
			login_menuItem.setEnabled(false);
			deleteUser_menuItem.setEnabled(false);
			createUser_menuItem.setEnabled(false);
			logout_menuItem.setEnabled(true);
			mainFrameGui.setUnlockedIcon();
		}catch(Exception ex){
			ex.printStackTrace();
			dialog.dynamicErrorDialogWindow("Login Faild", "Login Faild due wrong username and/or password/salt/iv-key");
			setLoginPanelOptions();
		}
	}

	public void setLoginPanelOptions(){
		removeUserLabel();
		login_menuItem.setEnabled(false);
		deleteUser_menuItem.setEnabled(true);
		createUser_menuItem.setEnabled(true);
		logout_menuItem.setEnabled(false);
		mainFrameGui.setLockedIcon();
	}

	public void setDeletePanelOptions(){
		removeUserLabel();
		deleteUser_menuItem.setEnabled(false);
		login_menuItem.setEnabled(true);
		createUser_menuItem.setEnabled(true);
		logout_menuItem.setEnabled(false);
		mainFrameGui.setLockedIcon();
	}

	public void setCreatePanelOptions(){
		removeUserLabel();
		createUser_menuItem.setEnabled(false);
		login_menuItem.setEnabled(true);
		deleteUser_menuItem.setEnabled(true);
		logout_menuItem.setEnabled(false);
		mainFrameGui.setLockedIcon();
	}

	
	
}
