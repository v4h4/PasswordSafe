package GUI_tableLogics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class JTablePopupDataModification {
	private JTableStructure ts =null;
	private JPopupMenu modifyTablePopup = null;
	private JPanel tablePanel = null;
	private JButton button_addPwd = null;
	private JButton button_changePwd = null;
	private JButton button_Cancel = null;
	private JButton button_showPwd = null;
	private JTextField textFieldOrg = null;
	private JTextField textFieldEmail = null;
	private JTextField textFieldUsr = null;
	private JPasswordField passwordField = null;
	
	public JTablePopupDataModification(JTableStructure ts){
		this.ts=ts;
		createModifyTablePopup();
	}
	
	private void createModifyTablePopup(){
		createTablePanel();
		modifyTablePopup = new JPopupMenu("Modify Table");
		modifyTablePopup.setSize(new Dimension(835, 165));
		modifyTablePopup.add(tablePanel).setBounds(10, 10, 820, 125);
	}
	
	private void createTablePanel(){
		tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createEtchedBorder());
		createOrgTextFieldAndOrgPanel();
		createEmailTextFieldAndEmailPanel();
		createUsrTextFieldAndUsrPanel();
		createPasswordFieldAndPwdPanel();
		createAddPasswordButton();
		createChangePasswordButton();
		createShowPasswordButton();
		createCancleButton();
	}
	
	private void createOrgTextFieldAndOrgPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Organisation", 0, 0, new Font("Arial", Font.BOLD, 13)));
		textFieldOrg = new JTextField();
		textFieldOrg.setBorder(BorderFactory.createEtchedBorder());
		panel.add(textFieldOrg).setBounds(10, 15, 180, 30);
		tablePanel.setBorder(BorderFactory.createEtchedBorder());
		tablePanel.add(panel).setBounds(10, 10, 200, 55);
	}
	
	private void createEmailTextFieldAndEmailPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Email", 0, 0, new Font("Arial", Font.BOLD, 13)));
		textFieldEmail = new JTextField();
		textFieldEmail.setBorder(BorderFactory.createEtchedBorder());
		panel.add(textFieldEmail).setBounds(10, 15, 180, 30);
		tablePanel.add(panel).setBounds(210, 10, 200, 55);
	}
	
	private void createUsrTextFieldAndUsrPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Username", 0, 0, new Font("Arial", Font.BOLD, 13)));
		textFieldUsr = new JTextField();
		textFieldUsr.setBorder(BorderFactory.createEtchedBorder());
		panel.add(textFieldUsr).setBounds(10, 15, 180, 30);
		tablePanel.add(panel).setBounds(410, 10, 200, 55);
	}

	private void createPasswordFieldAndPwdPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter Password", 0, 0, new Font("Arial", Font.BOLD, 13)));
		passwordField = new JPasswordField();
		passwordField.setBorder(BorderFactory.createEtchedBorder());
		panel.add(passwordField).setBounds(10, 15, 180, 30);
		tablePanel.add(panel).setBounds(610, 10, 200, 55);
	}
	
	private void createAddPasswordButton(){
		button_addPwd = new JButton("Add New Password");
		button_addPwd.setVisible(false);
		button_addPwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean result=ts.updateOneRowAtTable(textFieldOrg.getText(),textFieldEmail.getText(),textFieldUsr.getText(), new String(passwordField.getPassword()));
				if(result==true){
					hideTableDialog();
				}
			}
		});
		tablePanel.add(button_addPwd).setBounds(450, 73, 320, 40);
	}
	
	private void createChangePasswordButton(){
		button_changePwd = new JButton("Change Password");
		button_changePwd.setVisible(false);
		//showAndHidePassword();
		passwordField.setEchoChar('*');
		button_changePwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.getTableData()[ts.getSelectedRow()][1]=textFieldOrg.getText();
				ts.getTableData()[ts.getSelectedRow()][2]=textFieldEmail.getText();
				ts.getTableData()[ts.getSelectedRow()][3]=textFieldUsr.getText();
				ts.getTableData()[ts.getSelectedRow()][4]=new String(passwordField.getPassword());
				ts.getTableData()[ts.getSelectedRow()][5]=getCurrentDate();
				ts.getTableData()[ts.getSelectedRow()][6]=getCurrentTime();
				ts.updateEntireTable();
				hideTableDialog();
			}
		});
		tablePanel.add(button_changePwd).setBounds(450, 73, 320, 40);
	}
	
	private void createShowPasswordButton(){
		button_showPwd = new JButton("Show Password");
		button_showPwd.setVisible(false);
		button_showPwd.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				passwordField.setEchoChar('*');
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				passwordField.setEchoChar((char)0);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				passwordField.setEchoChar('*');
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		tablePanel.add(button_showPwd).setBounds(450, 73, 320, 40);
	}
	
	private void createCancleButton(){
		button_Cancel = new JButton("Cancel");
		button_Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideTableDialog();
			}
		});
		tablePanel.add(button_Cancel).setBounds(50, 73, 320, 40);
	}
	
	private void enableTextfields(int row){
		if(row == -1){
			textFieldOrg.setText("");
			textFieldEmail.setText("");
			textFieldUsr.setText("");
			passwordField.setText("");
		}
		if(row >= 0){
			textFieldOrg.setText(ts.getTableData()[row][1]);
			textFieldEmail.setText(ts.getTableData()[row][2]);
			textFieldUsr.setText(ts.getTableData()[row][3]);
			passwordField.setText(ts.getTableData()[row][4]);
			
		}
		textFieldOrg.setEditable(true);
		textFieldEmail.setEditable(true);
		textFieldUsr.setEditable(true);
		passwordField.setEditable(true);
	}
	
	private void disableTextfields(int row){
		textFieldOrg.setText(ts.getTableData()[row][1]);
		textFieldEmail.setText(ts.getTableData()[row][2]);
		textFieldUsr.setText(ts.getTableData()[row][3]);
		passwordField.setText(ts.getTableData()[row][4]);
		textFieldOrg.setEditable(false);
		textFieldEmail.setEditable(false);
		textFieldUsr.setEditable(false);
		passwordField.setEditable(false);
	}
	
	private String getCurrentDate(){
		DateFormat dt =new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dt.format(date);
	}
	
	private String getCurrentTime(){
		DateFormat dt =new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dt.format(date);
	}
	
	public void hideTableDialog(){
		textFieldOrg.setText("");
		textFieldEmail.setText("");
		textFieldUsr.setText("");
		passwordField.setText("");
		modifyTablePopup.setVisible(false);
		button_addPwd.setVisible(false);
		button_changePwd.setVisible(false);
		modifyTablePopup.setVisible(false);
	}
	
	public void showTableDialog_addPwd(){
		button_showPwd.setVisible(false);
		button_changePwd.setVisible(false);
		button_addPwd.setVisible(true);
		enableTextfields(-1);
		modifyTablePopup.show(ts.getMouseComponent(), ts.getMouseComponent().getX(), ts.getMouseComponent().getX()+50);
	}
	
	public void showTableDialog_changePwd(int row){
		button_showPwd.setVisible(false);
		button_addPwd.setVisible(false);
		button_changePwd.setVisible(true);
		enableTextfields(row);
		modifyTablePopup.show(ts.getMouseComponent(), ts.getMouseComponent().getX(), ts.getMouseComponent().getX()+50);
	}
	
	public void showTableDialog_showPwd(int row){
		button_addPwd.setVisible(false);
		button_changePwd.setVisible(false);
		button_showPwd.setVisible(true);
		disableTextfields(row);
		modifyTablePopup.show(ts.getMouseComponent(), ts.getMouseComponent().getX(), ts.getMouseComponent().getX()+50);
	}
	
	/*private void showAndHidePassword(){
	Runnable auto = new Runnable() {
		public void run() {
			while(1<2){
				try{
					Thread.sleep(2000);
					if(showing_password == true){
						Thread.sleep(8000);
						passwordField.setEchoChar('*');
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	};
	new Thread(auto).start();
}*/
	
}
