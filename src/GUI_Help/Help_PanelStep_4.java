package GUI_Help;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import SecureFileManager.FileChoosers;

public class Help_PanelStep_4 {
	private JPanel jpanel_step_1 = null;
	private Font smallTitleFont = null;
	private FileChoosers fc=null;
	private JTextArea instructions=null;
	public Help_PanelStep_4(FileChoosers fc){
		this.fc=fc;
		this.smallTitleFont = new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(15, 17, 15));
		createJPanelStep1();
	}
	
	private void createJPanelStep1(){
		jpanel_step_1 = new JPanel();
		jpanel_step_1.setLayout(null);
		jpanel_step_1.setBorder(BorderFactory.createEtchedBorder());
		jpanel_step_1.setVisible(true);
		createPath1_unlimitid_local_policy_Button();
		createPath2_unlimitid_US_export_policy_Button();
		addDscr_processToPanel();
		addInstructionsToPanel();
		addLinuxTipsToPanel();
	}
	
	private void addDscr_processToPanel(){
		JButton path1=createPath1_unlimitid_local_policy_Button();
		jpanel_step_1.add(path1).setBounds(15, 10, 235, 30);
		
		JButton path2 = createPath2_unlimitid_US_export_policy_Button();
		jpanel_step_1.add(path2).setBounds(270, 10, 235, 30);
		
		JTextArea descr_process = createInfoJTextArea("Arial", true, fc.getFontSizeForCorrectOP(11, 13, 11));
		descr_process.setText(
				"Accordingly to the installation proccess, it has not been successfull."+
				"By pressing the buttons above you will copy the files to the "+
				" application folder. Then you have to manually replace the selected files "+
				"in step 1 to 3.");
		jpanel_step_1.add(descr_process).setBounds(15, 50, 490, 65);
		
	}
	
	private void addInstructionsToPanel(){
		instructions = createInfoJTextArea("Arial", true, fc.getFontSizeForCorrectOP(10, 12, 10));
		updateInstructions();
		jpanel_step_1.add(instructions).setBounds(15, 117, 490, 75);
	}
		
	private void addLinuxTipsToPanel(){
		JLabel step3_1 = new JLabel();
		step3_1.setFont(smallTitleFont);
		step3_1.setText("In Linux:");
		jpanel_step_1.add(step3_1).setBounds(15, 200, 520, 30);
		
		JTextArea linux_tips = createInfoJTextArea("Arial", true, fc.getFontSizeForCorrectOP(11, 13, 11));
		linux_tips.setText(
				"You might have to remove the security files above as a root\n"+
				"(By using sudo in the terminal).");
		jpanel_step_1.add(linux_tips).setBounds(25, 225, 470, 50);
	}
	
	public JPanel getJPanel(){
		return this.jpanel_step_1;
	}

	private JButton createPath1_unlimitid_local_policy_Button(){
		JButton path1 = new JButton();
		path1.setText("Unlimited local_policy.jar");
		path1.setFont(new Font("Arial",Font.BOLD,fc.getFontSizeForCorrectOP(11, 13, 11)));
		path1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fc.writeUnlimitedFileToApplicationFolder("local_policy.jar");
				fc.writeUnlimitedFileToApplicationFolder("US_export_policy.jar");
			}
		});
		return path1;
	}
	
	private JButton createPath2_unlimitid_US_export_policy_Button(){
		JButton path2 = new JButton();
		path2.setText("Unlimited US_export_policy.jar");
		path2.setFont(new Font("Arial",Font.BOLD,fc.getFontSizeForCorrectOP(11, 13, 11)));
		path2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fc.writeUnlimitedFileToApplicationFolder("local_policy.jar");
				fc.writeUnlimitedFileToApplicationFolder("US_export_policy.jar");
			}
		});
		return path2;
	}

	private JTextArea createInfoJTextArea(String font, boolean bold, int size){
		JTextArea jtextArea = new JTextArea();
		if(bold==true){
			jtextArea.setFont(new Font(font,Font.BOLD,fc.getFontSizeForCorrectOP((int)(size*0.60), size, (int)(size*0.60))));
		}else{
			jtextArea.setFont(new Font(font,Font.PLAIN,fc.getFontSizeForCorrectOP((int)(size*0.60), size, (int)(size*0.60))));
		}
		jtextArea.setLineWrap(true);
		jtextArea.setWrapStyleWord(true);
		jtextArea.setBackground(new Color(0,0,0,0));
		//descr_process.setBorder(BorderFactory.createEtchedBorder());
		jtextArea.setEnabled(false);
		jtextArea.setDisabledTextColor(Color.BLACK);
		return jtextArea;
	}
	
	@SuppressWarnings("static-access")
	public void updateInstructions(){
		//if(instructions!=null){
			instructions.setText(
					"The following files that you selected in step 1-3, has not been replaced."+
					"You have to manuelly erase them(path 3 & 4), then copy the files with the "+
					"unlimited streangth files that you have copied to the application folder "+
					"by having pressed the buttons above"+
					"\n[Path 3]   * "+fc.getPath_to_target_local_policy()+
					"\n[Path 4]   * "+fc.getPath_to_target_us_export_policy());
		}
	//}
}
