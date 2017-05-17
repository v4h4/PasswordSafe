package GUI_Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.luan.LUANObject;

import SecureFileManager.FileManager;

public class PanelLoginGui {
	private JPanel loginPanel = null;
	private FileManager fm = null;
	private DialogGui dialog = null;
	private MainFrameGui mainFrame=null;
	private JComboBox <String> comboBoxSelectAlgorithm = null;
	private JComboBox <String> comboBoxKeySize = null;
	private JComboBox <String> comboBoxPasswordIterations = null;
	private JComboBox <String> comboBoxSelectuser = null;
	private JPasswordField passwordFieldEncryptionKey = null;
	private JPanel panelEncryptionKeyInfo=null;
	private JPasswordField passwordFieldIvKey=null;
	private JPanel panelFieldIvKeyInfo=null;
	private JPasswordField saltField =null;
	private JPanel panelFieldSaltInfo=null;
	private JLabel labelSalt =null;
	private JLabel labelEncryptionKey =null;
	private JLabel labelIvKey=null;
	private JButton buttonLogin=null;
	private boolean createNewUser=false;
	
	public PanelLoginGui(MainFrameGui mainFrame, DialogGui dialog){
		this.mainFrame = mainFrame;
		this.fm = new FileManager();
		this.dialog = dialog;
		createLoginJPanel();
	}
	
	private void createLoginJPanel(){
		initlilizeLoginPanel();
		addComboBoxAlghoritmToPanel();
		addComboBoxSelectUserToPanel();
		addComboBoxKeySizeToPanel();
		addComboBoxPasswordIterationsToPanel();
		addLabelIvKeyToFrame();
		addLabelEncryptionKeyToFrame();
		addPanelEncryptionKeyInfoToFrame();
		addInitialVectorFieldToPanel();
		addLabelSaltToFrame();
		addSaltFiledAndPanelToFrame();
		addLoginButtonToFrame();
		getCharactersHintByKeySize();
		isIvKeyCorrect();
		isKeyCorrect();
		isSaltCorrect();
	}
	
	private void initlilizeLoginPanel(){
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBorder(BorderFactory.createEtchedBorder());
		loginPanel.setVisible(true);
	}
	
	private void addComboBoxAlghoritmToPanel(){
		String algorithms[]={"Select Algorithm","AES/CBC/PKCS5Padding","AES/CTR/PKCS5Padding","AES/ECB/PKCS5Padding","AES/GCM/PKCS5Padding","DES/CTR/PKCS5Padding"};
		comboBoxSelectAlgorithm = new JComboBox<String>(algorithms);
		Font font = new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(17, 20, 17));
		comboBoxSelectAlgorithm.setFont(font);
		loginPanel.add(comboBoxSelectAlgorithm).setBounds(10, 10, 260, 40);
		comboBoxSelectAlgorithm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(getSelectedAlgorithmMode().equals("Select Algorithm") || getSelectedUser().equals("Select User")){
					buttonLogin.setEnabled(false);
				}
				if(!getSelectedAlgorithmMode().equals("Select Algorithm") && !getSelectedUser().equals("Select User")){
					buttonLogin.setEnabled(true);
				}
				combinationBetweenKeySizeAndAlgorithmIsCorrect();
				isIvKeyCorrect();
				isKeyCorrect();
				isSaltCorrect();
			}
		});
	}
	
	private void addComboBoxSelectUserToPanel(){
		String algorithms[]={"Select User"};
		try{
			algorithms=fm.getUsers();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		comboBoxSelectuser = new JComboBox<String>(algorithms);
		Font font = new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(20, 20, 20));
		comboBoxSelectuser.setFont(font);
		loginPanel.add(comboBoxSelectuser).setBounds(245, 178, 225, 50);
		comboBoxSelectuser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSelectuser!=null && comboBoxSelectuser.getItemCount()>0){
					if(comboBoxSelectuser.getItemCount()>0 && getSelectedAlgorithmMode().equals("Select Algorithm") || getSelectedUser().equals("Select User")){
						buttonLogin.setEnabled(false);
					}
					else if(!getSelectedAlgorithmMode().equals("Select Algorithm") && !getSelectedUser().equals("Select User")){
						buttonLogin.setEnabled(true);
					}
				}
			}
		});	
	}
	
	private void addComboBoxKeySizeToPanel(){
		String keySizeArr[]={"256","192","128","64"};
		comboBoxKeySize = new JComboBox<String>(keySizeArr);
		Font font = new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(20, 20, 20));
		comboBoxKeySize.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Size", 0, 0, new Font("Arial", Font.BOLD, 12)));	
		comboBoxKeySize.setFont(font);
		loginPanel.add(comboBoxKeySize).setBounds(275, 5, 80, 50);
		comboBoxKeySize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				combinationBetweenKeySizeAndAlgorithmIsCorrect();
				isIvKeyCorrect();
				isKeyCorrect();
				isSaltCorrect();
				getCharactersHintByKeySize();
			}
		});
	}
	
	private String[] createPasswordIterationArray(){
		String[] pwdItr = new String[100];
		int nbr=0;
		for(int i=0;i<pwdItr.length;i++){
			nbr+=50000;
			pwdItr[i]=nbr+"";
		}
		return pwdItr;
	}
	
	private void addComboBoxPasswordIterationsToPanel(){
		comboBoxPasswordIterations = new JComboBox<String>(createPasswordIterationArray());
		Font font = new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(13, 17, 13));
		comboBoxPasswordIterations.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Pwd Iterations", 0, 0, new Font("Arial", Font.BOLD, 10)));	
		comboBoxPasswordIterations.setFont(font);
		loginPanel.add(comboBoxPasswordIterations).setBounds(360, 5, 110, 50);
		comboBoxPasswordIterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isIvKeyCorrect();
				isKeyCorrect();
				if(getSelectedAlgorithmMode().equals("Select Algorithm") || getSelectedUser().equals("Select User")){
					buttonLogin.setEnabled(false);
				}
				if(!getSelectedAlgorithmMode().equals("Select Algorithm") && !getSelectedUser().equals("Select User")){
					buttonLogin.setEnabled(true);
				}
				if(getSelectedAlgorithmMode().contains("DES")){
					panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (8 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
					panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (8 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
					
				}
				if(!getSelectedAlgorithmMode().contains("DES")){
					panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
					panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
					getCharactersHintByKeySize();
				}
			}
		});
	}
	
	private void addLabelEncryptionKeyToFrame(){
		this.labelEncryptionKey = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 25, 19));
		labelEncryptionKey.setFont(font);
		labelEncryptionKey.setHorizontalAlignment(JLabel.CENTER);
		labelEncryptionKey.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Status", 0, 0, new Font("Arial", Font.BOLD, 11)));
		loginPanel.add(labelEncryptionKey).setBounds(10, 55, 225, 50);
	}	
	
	private void addLabelIvKeyToFrame(){
		this.labelIvKey = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 21, 19));
		labelIvKey.setFont(font);
		labelIvKey.setHorizontalAlignment(JLabel.CENTER);
		labelIvKey.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Iv-Key Status", 0, 0, new Font("Arial", Font.BOLD, 11)));
		loginPanel.add(labelIvKey).setBounds(245, 55, 225, 50);
	}	
	
	private void addPanelEncryptionKeyInfoToFrame(){
		this.passwordFieldEncryptionKey= new JPasswordField();
		this.panelEncryptionKeyInfo = new JPanel();
		panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelEncryptionKeyInfo.setVisible(true);
		panelEncryptionKeyInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(35, 35, 35));
		passwordFieldEncryptionKey.setFont(font);
		passwordFieldEncryptionKey.setText("");
		panelEncryptionKeyInfo.add(passwordFieldEncryptionKey).setBounds(10, 20, 205, 30);
		loginPanel.add(panelEncryptionKeyInfo).setBounds(10, 105, 225, 60);
		passwordFieldEncryptionKey.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isKeyCorrect();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isKeyCorrect();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isKeyCorrect();	
			}
		});
	}
	
	private void addInitialVectorFieldToPanel(){
		this.passwordFieldIvKey = new JPasswordField();
		this.panelFieldIvKeyInfo = new JPanel();
		panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelFieldIvKeyInfo.setVisible(true);
		panelFieldIvKeyInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(35, 35, 35));
		passwordFieldIvKey.setFont(font);
		passwordFieldIvKey.setText("");
		panelFieldIvKeyInfo.add(passwordFieldIvKey).setBounds(10, 20, 205, 30);
		loginPanel.add(panelFieldIvKeyInfo).setBounds(245, 105, 225, 60);
		passwordFieldIvKey.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isIvKeyCorrect();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isIvKeyCorrect();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isIvKeyCorrect();	
			}
		});
	}
	
	private void addLabelSaltToFrame(){
		this.labelSalt = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 23, 19));
		labelSalt.setFont(font);
		labelSalt.setHorizontalAlignment(JLabel.CENTER);
		labelSalt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Salt Status", 0, 0, new Font("Arial", Font.BOLD, 14)));
		loginPanel.add(labelSalt).setBounds(10, 170, 225, 60);
	//	isIvKeyStatusInactivated();
	}	
	
	private void addSaltFiledAndPanelToFrame(){
		this.saltField= new JPasswordField();
		this.panelFieldSaltInfo = new JPanel();
		panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelFieldSaltInfo.setVisible(true);
		panelFieldSaltInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(28, 28, 28));
		saltField.setFont(font);
		saltField.setText("");
		panelFieldSaltInfo.add(saltField).setBounds(10, 20, 205, 30);
		loginPanel.add(panelFieldSaltInfo).setBounds(10, 235, 225, 60);
		saltField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isSaltCorrect();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isSaltCorrect();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isSaltCorrect();	
			}
		});
	}
	
	private void addLoginButtonToFrame(){
		this.buttonLogin = new JButton();
		buttonLogin.setText("LOGIN");
		buttonLogin.setFont(new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(35, 35, 35)));
		buttonLogin.setEnabled(false);
		loginPanel.add(buttonLogin).setBounds(245, 242, 225, 50);
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isIvKeyCorrect() && isKeyCorrect() && isSaltCorrect() && fm.isfileExcisting(comboBoxSelectuser.getSelectedItem().toString())){
					LUANObject luan = new LUANObject();
					try{
						luan.put("username", comboBoxSelectuser.getSelectedItem().toString());
						luan.put("algorithm", comboBoxSelectAlgorithm.getSelectedItem().toString());
						luan.put("salt", new String(saltField.getPassword()));
						luan.put("ivKey", new String(passwordFieldIvKey.getPassword()));
						luan.put("key", new String(passwordFieldEncryptionKey.getPassword()));
						luan.put("keySize", Integer.parseInt(comboBoxKeySize.getSelectedItem().toString()));
						luan.put("pwdIterations", Integer.parseInt(comboBoxPasswordIterations.getSelectedItem().toString()));
						luan=fm.readEncryptedPassword(luan);
						if(luan!= null){	
							if(luan.containsString("salt")){
								luan.removeString("salt");
							}
							if(luan.containsString("ivKey")){
								luan.removeString("ivKey");
							}
							if(luan.containsString("key")){
								luan.removeString("key");
							}
							if(luan.containsInteger("keySize")){
								luan.removeInteger("keySize");
							}
							if(luan.containsInteger("pwdIterations")){
								luan.removeInteger("pwdIterations");
							}
							luan.put("key", luan.getString("passwordTable_Key"));
							luan.put("ivKey", luan.getString("passwordTable_Iv-Key"));
							luan.put("salt", luan.getString("passwordTable_Salt"));
							luan.put("keySize", luan.getInteger("passwordTable_KeySize"));
							luan.put("pwdIterations", luan.getInteger("passwordTable_PwdIterations"));
							if(luan.containsString("passwordTable_Key")){
								luan.removeString("passwordTable_Key");
							}
							if(luan.containsString("passwordTable_Iv-Key")){
								luan.removeString("passwordTable_Iv-Key");
							}
							if(luan.containsString("passwordTable_Salt")){
								luan.removeString("passwordTable_Salt");
							}
							if(luan.containsInteger("passwordTable_KeySize")){
								luan.removeInteger("passwordTable_KeySize");
							}
							if(luan.containsInteger("passwordTable_PwdIterations")){
								luan.removeInteger("passwordTable_PwdIterations");
							}
							luan=fm.readEncryptedPasswordTable(luan);
							mainFrame.showMainPanel(luan);
						}else{
							dialog.dynamicErrorDialogWindow("Authentication Faild", "Wrong Iv-key and/or decryption-Key and/or password!");
						}
					}catch(Exception ex){
						dialog.dynamicErrorDialogWindow("Authentication Faild", "Wrong Iv-key and/or decryption-Key and/or password!");
					}	
				}
			}
		});
		
	}
	
	private boolean isIvKeyCorrect(){
		if(getSelectedAlgorithmMode().contains("AES/ECB")){
			labelIvKey.setForeground(new Color(0,150,0));
			labelIvKey.setText("NOT NEEDED");
			passwordFieldIvKey.setEnabled(false);
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			return true;
		}else if(!getSelectedAlgorithmMode().contains("DES") && passwordFieldIvKey.getPassword().length==16){
			labelIvKey.setForeground(new Color(0,150,0));
			labelIvKey.setText("IV-KEY CORRECT");
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			passwordFieldIvKey.setEnabled(true);
			return true;
		}else if(getSelectedAlgorithmMode().contains("DES") && passwordFieldIvKey.getPassword().length==8){
			labelIvKey.setForeground(new Color(0,150,0));
			labelIvKey.setText("IV-KEY CORRECT");
			passwordFieldIvKey.setEnabled(true);
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));//createEtchedBorder(new Color(0,150,0), new Color(0,150,0)));
			return true;
		}else{
			labelIvKey.setForeground(Color.RED);
			labelIvKey.setText("IV-KEY INCORRECT");
			passwordFieldIvKey.setEnabled(true);
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
	}
	
	private boolean isKeyCorrect(){
		if(getMinLengthByKeySize() <= passwordFieldEncryptionKey.getPassword().length && passwordFieldEncryptionKey.getPassword().length <= getMaxLengthByKeySize()){
			labelEncryptionKey.setForeground(new Color(0,150,0));
			passwordFieldEncryptionKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			labelEncryptionKey.setText("KEY CORRECT");
			return true;
		}
		else{
			labelEncryptionKey.setForeground(Color.RED);
			labelEncryptionKey.setText("KEY INCORRECT");
			passwordFieldEncryptionKey.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
	}
	
	private boolean isSaltCorrect(){
		if(getMinLengthByKeySize() <= saltField.getPassword().length && saltField.getPassword().length <= getMaxLengthByKeySize()){
			labelSalt.setForeground(new Color(0,150,0));
			labelSalt.setText("SALT CORRECT");
			saltField.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			return true;
		}else{
			labelSalt.setForeground(Color.RED);
			labelSalt.setText("SALT INCORRECT");
			saltField.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
	}
	
	private boolean combinationBetweenKeySizeAndAlgorithmIsCorrect(){
		if(comboBoxSelectAlgorithm.getSelectedItem().toString().contains("DES")){
			comboBoxKeySize.setSelectedItem(3);
		}
		if(getSelectedAlgorithmMode().contains("DES") && Integer.parseInt(comboBoxKeySize.getSelectedItem().toString())>64){
			comboBoxKeySize.setSelectedIndex(3);
			return false;
		}
		if(getSelectedAlgorithmMode().contains("AES") && Integer.parseInt(comboBoxKeySize.getSelectedItem().toString())<128){
			comboBoxKeySize.setSelectedIndex(2);
			return false;
		}
		if(comboBoxKeySize.getSelectedItem().toString().equals("64")){
			comboBoxSelectAlgorithm.setSelectedIndex(5);
		}
		return true;
	}
	
	private void getCharactersHintByKeySize(){
		if(comboBoxKeySize.getSelectedItem().toString().equals("256")){
			panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16-32 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (16-32 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(10, 18, 10));
			passwordFieldEncryptionKey.setFont(font);
			saltField.setFont(font);
			font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(18, 35, 18));
			passwordFieldIvKey.setFont(font);
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("192")){
			panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16-24 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (16-24 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(13, 24, 13));
			passwordFieldEncryptionKey.setFont(font);
			saltField.setFont(font);
			font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(18, 35, 18));
			passwordFieldIvKey.setFont(font);
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("128")){
			panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(18, 35, 18));
			passwordFieldEncryptionKey.setFont(font);
			saltField.setFont(font);
			passwordFieldIvKey.setFont(font);
		}
		else{
			panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (8 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (8 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (8 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
			Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(38, 70, 38));
			passwordFieldEncryptionKey.setFont(font);
			saltField.setFont(font);
			passwordFieldIvKey.setFont(font);
		}
	}
	
	private int getMaxLengthByKeySize(){
		if(comboBoxKeySize.getSelectedItem().toString().equals("256")){
			return 32;
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("192")){
			return 24;
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("128")){
			return 16;
		}
		else{
			return 8;
		}
	}
	
	private int getMinLengthByKeySize(){
		if(comboBoxKeySize.getSelectedItem().toString().equals("256")){
			return 16;
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("192")){
			return 16;
		}
		else if(comboBoxKeySize.getSelectedItem().toString().equals("128")){
			return 16;
		}
		else{
			return 8;
		}
	}
	
	public String getSelectedAlgorithmMode(){
		return this.comboBoxSelectAlgorithm.getSelectedItem().toString();
	}
	
	public String getSelectedUser(){
		return this.comboBoxSelectuser.getSelectedItem().toString();
	}
	
	public boolean isCurrentModeLogin(){
		return createNewUser;
	}
	
	public void showLoginPanel(){
		updateComboboxUser();
		loginPanel.setVisible(true);
	}
	
	private void updateComboboxUser(){
		comboBoxSelectuser.removeAllItems();
		String algorithms[]={"Select User"};// ifall fm.getUser skiter sig �r inte algorithms null
		try{
			algorithms=fm.getUsers();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		for(int i=0;i<algorithms.length;i++){
			comboBoxSelectuser.addItem(algorithms[i]);
		}
	}
	
	public void hideLoginPanel(){
		loginPanel.setVisible(false);
		saltField.setText("");
		passwordFieldEncryptionKey.setText("");
		passwordFieldIvKey.setText("");
		comboBoxSelectuser.setSelectedIndex(0);
		comboBoxSelectAlgorithm.setSelectedIndex(0);
		isIvKeyCorrect();
		isIvKeyCorrect();
		isSaltCorrect();
		
	}
	
	public JPanel getJPanel(){
		return this.loginPanel;
	}
}
