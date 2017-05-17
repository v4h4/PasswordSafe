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
import javax.swing.JTextField;

import org.luan.LUANObject;

import SecureFileManager.FileManager;

public class PanelCreateUserGui {
	private FileManager fm=null;
	private MainFrameGui mainFrame=null;
	private JPanel createNewUserPanel = null;
	private JComboBox <String> comboBoxSelectAlgorithm = null;
	private JComboBox <String> comboBoxKeySize = null;
	private JComboBox <String> comboBoxPasswordIterations = null;
	private JPasswordField passwordFieldEncryptionKey = null;
	private JPanel panelEncryptionKeyInfo=null;
	private JPasswordField passwordFieldIvKey=null;
	private JPanel panelFieldIvKeyInfo=null;
	private JPasswordField saltField =null;
	private JPanel panelFieldSaltInfo=null;
	private JLabel labelSalt =null;
	private JLabel labelEncryptionKey =null;
	private JLabel labelIvKey=null;
	private JLabel labelUsername=null;
	private JTextField textFieldUsername= null;
	private JPanel panelUsernameInfo=null;
	private JButton buttonCreateNewUser=null;
	private boolean createNewUser=false;
	private DialogGui dialog = null;	
	public PanelCreateUserGui(MainFrameGui mainFrame, DialogGui dialog){
		this.mainFrame=mainFrame;
		this.fm= new FileManager();
		this.dialog=dialog;
		createNewUserJPanel();
	}
	
	private void createNewUserJPanel(){
		initilizeCreateNewUserPanel();
		addComboBoxTo_CreateNewUserPanel();
		addComboBoxKeySizeToPanel();
		addComboBoxPasswordIterationsToPanel();
		addLabelIvKeyTo_CreateNewUserPanel();
		addLabelEncryptionKeyTo_CreateNewUserPanel();
		addPanelEncryptionKeyInfoTo_CreateNewUserPanel();
		addInitialVectorFieldTo_CreateNewUserPanel();
		addLabelSaltTo_CreateNewUserPanel();
		addSaltFieldAndPanelTo_CreateNewUserPanel();
		addLabelUsernameTo_CreateNewUserPanel();
		addPanelAndTextFieldUsernameTo__CreateNewUserPanel();
		addCreateNewUserButtonTo_CreateNewUserPanel();
		getCharactersHintByKeySize();
		isIvKeyCorrect();
		isKeyCorrect();
		isSaltCorrect();
		isUsernameCorrect();
	}
	
	private void initilizeCreateNewUserPanel(){
		createNewUserPanel = new JPanel();
		createNewUserPanel.setLayout(null);
		createNewUserPanel.setBorder(BorderFactory.createEtchedBorder());
		createNewUserPanel.setVisible(false);
	}

	private void addComboBoxTo_CreateNewUserPanel(){
		String algorithms[]={"Select Algorithm","AES/CBC/PKCS5Padding","AES/CTR/PKCS5Padding","AES/ECB/PKCS5Padding","AES/GCM/PKCS5Padding","DES/CTR/PKCS5Padding"};
		comboBoxSelectAlgorithm = new JComboBox<String>(algorithms);
		Font font = new Font("Consolas", Font.BOLD, fm.getFontSizeForCorrectOP(17, 20, 17));
		comboBoxSelectAlgorithm.setFont(font);
		createNewUserPanel.add(comboBoxSelectAlgorithm).setBounds(10, 10, 260, 40);
		comboBoxSelectAlgorithm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getSelectedAlgorithmMode().equals("Select Algorithm")){
					buttonCreateNewUser.setEnabled(false);
				}
				if(!getSelectedAlgorithmMode().equals("Select Algorithm")){
					buttonCreateNewUser.setEnabled(true);
				}
				combinationBetweenKeySizeAndAlgorithmIsCorrect();
				isIvKeyCorrect();
				isKeyCorrect();
				isSaltCorrect();
			}
		});
	}
	
	private void addComboBoxKeySizeToPanel(){
		String keySizeArr[]={"256","192","128","64"};
		comboBoxKeySize = new JComboBox<String>(keySizeArr);
		Font font = new Font("Consolas", Font.BOLD, 20);
		comboBoxKeySize.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Size", 0, 0, new Font("Arial", Font.BOLD, 12)));	
		comboBoxKeySize.setFont(font);
		createNewUserPanel.add(comboBoxKeySize).setBounds(275, 5, 80, 50);
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
		createNewUserPanel.add(comboBoxPasswordIterations).setBounds(360, 5, 110, 50);
		comboBoxPasswordIterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isIvKeyCorrect();
				isKeyCorrect();
				if(getSelectedAlgorithmMode().equals("Select Algorithm")){
					buttonCreateNewUser.setEnabled(false);
				}
				if(!getSelectedAlgorithmMode().equals("Select Algorithm")){
					buttonCreateNewUser.setEnabled(true);
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
	
	private void addLabelEncryptionKeyTo_CreateNewUserPanel(){
		this.labelEncryptionKey = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 25, 19));
		labelEncryptionKey.setFont(font);
		labelEncryptionKey.setHorizontalAlignment(JLabel.CENTER);
		labelEncryptionKey.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Status", 0, 0, new Font("Arial", Font.BOLD, 11)));
		createNewUserPanel.add(labelEncryptionKey).setBounds(10, 55, 225, 50);
	}	
	
	private void addLabelIvKeyTo_CreateNewUserPanel(){
		this.labelIvKey = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 21, 19));
		labelIvKey.setFont(font);
		labelIvKey.setHorizontalAlignment(JLabel.CENTER);
		labelIvKey.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Iv-Key Status", 0, 0, new Font("Arial", Font.BOLD, 11)));
		createNewUserPanel.add(labelIvKey).setBounds(245, 55, 225, 50);
	}	
	
	private void addPanelEncryptionKeyInfoTo_CreateNewUserPanel(){
		this.passwordFieldEncryptionKey= new JPasswordField();
		this.panelEncryptionKeyInfo = new JPanel();
		panelEncryptionKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelEncryptionKeyInfo.setVisible(true);
		panelEncryptionKeyInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(33, 35, 33));
		passwordFieldEncryptionKey.setFont(font);
		passwordFieldEncryptionKey.setText("");
		panelEncryptionKeyInfo.add(passwordFieldEncryptionKey).setBounds(10, 20, 205, 30);
		createNewUserPanel.add(panelEncryptionKeyInfo).setBounds(10, 105, 225, 60);
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
	
	private void addInitialVectorFieldTo_CreateNewUserPanel(){
		this.passwordFieldIvKey = new JPasswordField();
		this.panelFieldIvKeyInfo = new JPanel();
		panelFieldIvKeyInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Iv-Key (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelFieldIvKeyInfo.setVisible(true);
		panelFieldIvKeyInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(33, 35, 33));
		passwordFieldIvKey.setFont(font);
		passwordFieldIvKey.setText("");
		panelFieldIvKeyInfo.add(passwordFieldIvKey).setBounds(10, 20, 205, 30);
		createNewUserPanel.add(panelFieldIvKeyInfo).setBounds(245, 105, 225, 60);
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
	
	private void addLabelSaltTo_CreateNewUserPanel(){
		this.labelSalt = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(19, 23, 19));
		labelSalt.setFont(font);
		labelSalt.setHorizontalAlignment(JLabel.CENTER);
		labelSalt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Salt Status", 0, 0, new Font("Arial", Font.BOLD, 14)));
		createNewUserPanel.add(labelSalt).setBounds(10, 170, 225, 60);
	}	
	
	private void addSaltFieldAndPanelTo_CreateNewUserPanel(){
		this.saltField= new JPasswordField();
		this.panelFieldSaltInfo = new JPanel();
		panelFieldSaltInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Salt (16 Characters)", 0, 0, new Font("Arial", Font.BOLD, 11)));		
		panelFieldSaltInfo.setVisible(true);
		panelFieldSaltInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(28, 28, 28));
		saltField.setFont(font);
		saltField.setText("");
		panelFieldSaltInfo.add(saltField).setBounds(10, 20, 205, 30);
		createNewUserPanel.add(panelFieldSaltInfo).setBounds(10, 235, 225, 60);
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
	
	private void addCreateNewUserButtonTo_CreateNewUserPanel(){
		this.buttonCreateNewUser = new JButton();
		buttonCreateNewUser.setText("Create New User");
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(33, 35, 33));
		buttonCreateNewUser.setFont(font);
		createNewUserPanel.add(buttonCreateNewUser).setBounds(12, 305, 456, 50);
		buttonCreateNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isIvKeyCorrect() && isKeyCorrect() && isSaltCorrect() && isUsernameCorrect()){
					LUANObject luan = new LUANObject();
					try{
						luan.put("username", getUsername());
						luan.put("algorithm", comboBoxSelectAlgorithm.getSelectedItem().toString());
						luan.put("salt", new String(saltField.getPassword()));
						luan.put("ivKey", new String(passwordFieldIvKey.getPassword()));
						luan.put("key", new String(passwordFieldEncryptionKey.getPassword()));
						luan.put("pwdIterations", Integer.parseInt(comboBoxPasswordIterations.getSelectedItem().toString()));
						luan.put("keySize", Integer.parseInt(comboBoxKeySize.getSelectedItem().toString()));
						fm.createNewUsername(luan);
						dialog.dynamicWarningDialogWindow("Creation of new user: successfull", "You have created a new user:  "+textFieldUsername.getText().toLowerCase());
						mainFrame.showLoginPanel();
					}catch(Exception ex){
						dialog.dynamicErrorDialogWindow("Creation of new user: faild", "You messed up with a simple task,,,, try again");
					}	
				}
			}
		});
	}
	
	private void addLabelUsernameTo_CreateNewUserPanel(){
		this.labelUsername = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(17, 17, 17));
		labelUsername.setFont(font);
		labelUsername.setHorizontalAlignment(JLabel.CENTER);
		labelUsername.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Username Status", 0, 0, new Font("Arial", Font.BOLD, 11)));
		createNewUserPanel.add(labelUsername).setBounds(245, 170, 225, 60);
	}
	
	
	private void addPanelAndTextFieldUsernameTo__CreateNewUserPanel(){
		this.textFieldUsername= new JTextField();
		this.panelUsernameInfo = new JPanel();
		panelUsernameInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Username", 0, 0, new Font("Arial", Font.BOLD, 11)));
		panelUsernameInfo.setVisible(true);
		panelUsernameInfo.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(20, 20, 20));
		textFieldUsername.setFont(font);
		panelUsernameInfo.add(textFieldUsername).setBounds(10, 20, 205, 30);
		createNewUserPanel.add(panelUsernameInfo).setBounds(245, 235, 225, 60);
		textFieldUsername.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isUsernameCorrect();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isUsernameCorrect();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isUsernameCorrect();
			}
		});
	}
	
	private boolean isUsernameCorrect(){
		if(fm.isfileExcisting(getUsername())==true){
			labelUsername.setForeground(Color.RED);
			labelUsername.setText("USERNAME ALREADY TAKEN");
			textFieldUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
			Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(12, 14, 12));
			labelUsername.setFont(font);
			return false;
		}
		if(textFieldUsername.getText().length()>2 && textFieldUsername.getText().length()<18  ){
			labelUsername.setForeground(new Color(0,150,0));
			labelUsername.setText("USERNAME CORRECT");
			textFieldUsername.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(15, 17, 15));
			labelUsername.setFont(font);
			return true;
		}else{
			labelUsername.setForeground(Color.RED);
			labelUsername.setText("USERNAME INCORRECT");
			textFieldUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
			Font font = new Font("Ariel", Font.BOLD, fm.getFontSizeForCorrectOP(15, 17, 15));
			labelUsername.setFont(font);
			return false;
		}
	}
	
	private boolean isIvKeyCorrect(){
		if(getSelectedAlgorithmMode().contains("AES/ECB")){
			labelIvKey.setForeground(new Color(0,150,0));
			labelIvKey.setText("NOT NEEDED");
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			passwordFieldIvKey.setEnabled(false);
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
			passwordFieldIvKey.setBorder(BorderFactory.createLineBorder(new Color(0,150,0)));
			passwordFieldIvKey.setEnabled(true);
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
	
	public int getSelectedKeySize(){
		return Integer.parseInt(this.comboBoxKeySize.getSelectedItem().toString());
	}
	
	public int getSelectedPasswordIteration(){
		return Integer.parseInt(this.comboBoxPasswordIterations.getSelectedItem().toString());
	}

	public boolean isCurrentModeLogin(){
		return createNewUser;
	}
	
	public JPanel getJPanel(){
		return this.createNewUserPanel;
	}
	
	private String getUsername(){
		return textFieldUsername.getText().toString();
	}
	
	public void showCreateNewUserPanel(){
		createNewUserPanel.setVisible(true);
		//getCharactersHintByKeySize();
	}
	
	public void hideCreateNewUserPanel(){
		createNewUserPanel.setVisible(false);
		saltField.setText("");
		passwordFieldEncryptionKey.setText("");
		passwordFieldIvKey.setText("");
		textFieldUsername.setText("");
		comboBoxSelectAlgorithm.setSelectedIndex(0);
		isIvKeyCorrect();
		isIvKeyCorrect();
		isSaltCorrect();
		isUsernameCorrect();
	}
}