package GUI_Help;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SecureFileManager.FileChoosers;
import SecureFileManager.FileManager;

public class Help_PanelStep_2 {
	private JPanel jpanel_step_2 = null;
	private Font smallTitleFont = null;
	private Font textFont = null;
	private String local_policy_path=null;
	private String us_export_path=null;
	private FileChoosers fc = null;
	private FileManager fm = null;
	
	public Help_PanelStep_2(FileChoosers fc){
		this.fc = fc;
		this.smallTitleFont = new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(15, 17, 15));
		this.textFont = new Font("Arial", Font.PLAIN, fc.getFontSizeForCorrectOP(14, 16, 14));
		this.fm= new FileManager();
		createJPanelStep1();
		createStep1();
		createStep2();
		createStep3();
		createButtonLocalPolicy();
		createButtonUS_export_Policy();
	}
	
	private void createJPanelStep1(){
		jpanel_step_2 = new JPanel();
		jpanel_step_2.setLayout(null);
		jpanel_step_2.setBorder(BorderFactory.createEtchedBorder());
		jpanel_step_2.setVisible(true);
	}
	
	private void createStep1(){
		JLabel step1_1 = new JLabel();
		step1_1.setFont(smallTitleFont);
		step1_1.setText("In Windows OS:");
		jpanel_step_2.add(step1_1).setBounds(15, 10, 520, 30);
		
		JLabel step1_2 = new JLabel();
		step1_2.setFont(textFont);
		step1_2.setText("In windos it might be located at: C:\\Program Files\\Java\\jre8\\lib\\security");
		jpanel_step_2.add(step1_2).setBounds(25, 30, 520, 30);
	}
	
	private void createStep2(){
		JLabel step2_1 = new JLabel();
		step2_1.setFont(smallTitleFont);
		step2_1.setText("In Linux OS:");
		jpanel_step_2.add(step2_1).setBounds(15, 70, 520, 30);		
		
		JLabel step2_2 = new JLabel();
		step2_2.setFont(textFont);
		step2_2.setText("In linux it might be located at: usr/Java/jre8/lib/security");
		jpanel_step_2.add(step2_2).setBounds(25, 90, 520, 30);
		
	}
	
	private void createStep3(){
		JLabel step3_1 = new JLabel();
		step3_1.setFont(smallTitleFont);
		step3_1.setText("In Apple OS (Mac):");
		jpanel_step_2.add(step3_1).setBounds(15, 130, 520, 30);		
		
		JLabel step3_2 = new JLabel();
		step3_2.setFont(textFont);
		step3_2.setText("On mac it might be located at: /Library/Java/Home/lib/security");
		jpanel_step_2.add(step3_2).setBounds(25, 150, 520, 30);
	}
	
	private void createButtonLocalPolicy(){
		JButton button_localPolicy = new JButton();
		button_localPolicy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(9, 13, 9)));
		button_localPolicy.setText("Locate local_policy.jar");
		button_localPolicy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				local_policy_path=fc.savePathToFile("local_policy.jar");
			}
		});
		jpanel_step_2.add(button_localPolicy).setBounds(20, 180, 230, 30);}
	
	private void createButtonUS_export_Policy(){
		JButton button_US_export_Policy = new JButton();
		button_US_export_Policy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(9, 13, 9)));
		button_US_export_Policy.setText("Locate US_export_policy.jar");
		button_US_export_Policy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				us_export_path=fc.savePathToFile("US_export_policy.jar");
			}
		});
		jpanel_step_2.add(button_US_export_Policy).setBounds(270, 180, 230, 30);
	}
	
	public JPanel getJPanel(){
		return this.jpanel_step_2;
	}
	
	public String get_local_policy_Path(){
		return this.local_policy_path;
	}
	
	public String get_US_export_policyPath(){
		return this.us_export_path;
	}
}
