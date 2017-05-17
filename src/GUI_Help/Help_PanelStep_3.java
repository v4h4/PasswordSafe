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
public class Help_PanelStep_3 {
	private JPanel jpanel_step_3 = null;
	private Font smallTitleFont = null;
	private Font textFont = null;
	private FileChoosers fc=null;
	private FileManager fm =null;
	public Help_PanelStep_3(FileChoosers fc){
		this.fm = new FileManager();
		this.fc = fc;
		this.smallTitleFont = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(15, 17, 15));
		this.textFont = new Font("Arial", Font.PLAIN, fm.getFontSizeForCorrectOP(14, 16, 14));
		createJPanelStep1();
		createStep1();
		createStep2();
		createStep3();
		createButtonLocalPolicy();
		createButtonUS_export_Policy();
		createButton_change_local_policy();
		createButton_change_US_export_policy();
	}
	
	private void createJPanelStep1(){
		jpanel_step_3 = new JPanel();
		jpanel_step_3.setLayout(null);
		jpanel_step_3.setBorder(BorderFactory.createEtchedBorder());
		jpanel_step_3.setVisible(true);
	}
	
	private void createStep1(){
		JLabel step1_1 = new JLabel();
		step1_1.setFont(smallTitleFont);
		step1_1.setText("In Windows OS:");
		jpanel_step_3.add(step1_1).setBounds(15, 10, 520, 30);
		
		JLabel step1_2 = new JLabel();
		step1_2.setFont(textFont);
		step1_2.setText("In windos it might be located at: C:\\Program Files\\Java\\jre8\\lib\\security");
		jpanel_step_3.add(step1_2).setBounds(25, 30, 520, 30);
	}
	
	private void createStep2(){
		JLabel step2_1 = new JLabel();
		step2_1.setFont(smallTitleFont);
		step2_1.setText("In Linux OS:");
		jpanel_step_3.add(step2_1).setBounds(15, 70, 520, 30);		
		
		JLabel step2_2 = new JLabel();
		step2_2.setFont(textFont);
		step2_2.setText("In linux it might be located at: usr/Java/jre8/lib/security");
		jpanel_step_3.add(step2_2).setBounds(25, 90, 520, 30);	
	}
	
	private void createStep3(){
		JLabel step3_1 = new JLabel();
		step3_1.setFont(smallTitleFont);
		step3_1.setText("In Apple OS (Mac):");
		jpanel_step_3.add(step3_1).setBounds(15, 130, 520, 30);		
		
		JLabel step3_2 = new JLabel();
		step3_2.setFont(textFont);
		step3_2.setText("On mac it might be located at: /Library/Java/Home/lib/security");
		jpanel_step_3.add(step3_2).setBounds(25, 150, 520, 30);
	}
	
	private void createButtonLocalPolicy(){
		JButton button_localPolicy = new JButton();
		button_localPolicy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(9, 13, 9)));
		button_localPolicy.setText("Locate local_policy.jar");
		button_localPolicy.setEnabled(false);
		jpanel_step_3.add(button_localPolicy).setBounds(20, 180, 230, 30);
	}
	
	private void createButtonUS_export_Policy(){
		JButton button_US_export_Policy = new JButton();
		button_US_export_Policy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(9, 13, 9)));
		button_US_export_Policy.setText("Locate US_export_policy.jar");
		button_US_export_Policy.setEnabled(false);
		jpanel_step_3.add(button_US_export_Policy).setBounds(270, 180, 230, 30);
	}
	
	private void createButton_change_local_policy(){
		JButton button_localPolicy = new JButton();
		button_localPolicy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(7, 10, 7)));
		button_localPolicy.setText("Change file above to unlimited Strength");
		button_localPolicy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fc.switch_local_policy_File();
			}
		});
		jpanel_step_3.add(button_localPolicy).setBounds(20, 220, 230, 30);
	}
	
	private void createButton_change_US_export_policy(){
		JButton button_US_export_Policy = new JButton();
		button_US_export_Policy.setFont( new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(6, 9, 6)));
		button_US_export_Policy.setText("Change file above to unlimited Strength");
		button_US_export_Policy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fc.switch_us_export_policy_File();
			}
		});
		jpanel_step_3.add(button_US_export_Policy).setBounds(270, 220, 230, 30);
	}
	
	public JPanel getJPanel(){
		return this.jpanel_step_3;
	}
	
	
}
