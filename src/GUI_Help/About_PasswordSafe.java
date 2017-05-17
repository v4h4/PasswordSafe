package GUI_Help;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import SecureFileManager.FileManager;

public class About_PasswordSafe {
	private JFrame about_window = null;
	private JPanel about_panel =null;
	private FileManager fm = null;
	public About_PasswordSafe(){
		this.fm= new FileManager();
		createAboutWindow();
	}
	
	private void createAboutWindow(){
		this.about_window= new JFrame();
		setLockedIcon();
		about_window.setTitle("PasswordSafe 1.0");
		about_window.setVisible(false);
		about_window.setSize(new Dimension(600, 540));
		about_window.setLayout(null);
		about_window.setResizable(false);
		about_window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		about_window.setLocation(200, 10);
		addPanelToAbout_window();
		addJLabelTileToPanel();
		addAbout_infoToPanel();
	}
	
	private void setLockedIcon(){
		about_window.setIconImage(fm.fileFromJar2Image("locked.png"));
	}
	
	private void addPanelToAbout_window(){
		about_panel = new JPanel();
		about_panel.setLayout(null);
		about_panel.setBorder(BorderFactory.createEtchedBorder());
		about_panel.setVisible(true);
		if(fm.os_is_windows()){
			about_window.add(about_panel).setBounds(20, 10, 550, 495);
		}else{
			about_window.add(about_panel).setBounds(20, 10, 550, 485);
		}
		
	}
	
	private void addJLabelTileToPanel(){
		JLabel label = new JLabel();
		label.setText("About PasswordSafe:");
		label.setFont(new Font("Arial",Font.BOLD,fm.getFontSizeForCorrectOP(35, 40, 35)));
		about_panel.add(label).setBounds(23, 10, 480,50);
	}
	
	private void addAbout_infoToPanel(){
		JTextArea jtextArea = new JTextArea();
		jtextArea.setFont(new Font("Arial",Font.BOLD,fm.getFontSizeForCorrectOP(14, 15, 14)));
		jtextArea.setLineWrap(true);
		jtextArea.setWrapStyleWord(true);
		jtextArea.setBackground(new Color(0,0,0,0));
		//jtextArea.setBorder(BorderFactory.createEtchedBorder());
		jtextArea.setEnabled(false);
		jtextArea.setDisabledTextColor(Color.BLACK);
		jtextArea.setHighlighter(null);
		jtextArea.setText(
				"I developed this application because on my jobb, we have diffrent " +
				"systems there we are not allowed to have the same password to all " +
				"of them. We have been directly told that each applications shall " +
				"have each password.\n\n"+ 
				
				"There are about minimum of 8 applications. That means all the " +
				"passwords I have on my private things, I aswell have to add " +
				"eight more passwords to my memory(brain). It will be too much.\n\n" +
				
				"Therefore I have created this application that can be used at " +
				"work. This application does not use a database or any kind of " +
				"webservice connected to a database to store the passwords on a " + 
				"remote place. All passwords are stored att the application folder " +
				"in the Users folder, and are encrypted\n\n" + 
				
				"This solution will provide double security, the first line of " + 
				"security is the works security structure such as for example the " + 
				"firewall. The second line is the encryption it self from this " + 
				"application.\n\n" +
				
				"Yours Sincerely,\n" +
				"v44h44 (Nerd Alias)");
		about_panel.add(jtextArea).setBounds(25, 70, 500, 400);
	}
	
	public void set_About_Window_Visible(){
		this.about_window.setVisible(true);
	}
	
	public void set_About_Window_Invisible(){
		this.about_window.setVisible(false);
	}
	
	
}
