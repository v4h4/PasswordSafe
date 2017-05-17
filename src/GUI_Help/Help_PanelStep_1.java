package GUI_Help;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import SecureFileManager.FileManager;

public class Help_PanelStep_1 {
	private JPanel jpanel_step_1 = null;
	private Font smallTitleFont = null;
	private Font textFont = null;
	private FileManager fm=null;
	public Help_PanelStep_1(){
		this.fm=new FileManager();
		this.smallTitleFont = new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(11, 17, 11));
		this.textFont = new Font("Arial", Font.PLAIN, fm.getFontSizeForCorrectOP(14, 16, 14));
		createJPanelStep1();
		inforSecurityJarReplacement();
		createStep1();
		createStep2();
		createStep3();
	}
	
	private void createJPanelStep1(){
		jpanel_step_1 = new JPanel();
		jpanel_step_1.setLayout(null);
		jpanel_step_1.setBorder(BorderFactory.createEtchedBorder());
		jpanel_step_1.setVisible(true);
	}
	
	private void inforSecurityJarReplacement(){
		String info_String= "Java only allows maxium 128-bit encryption as a standard, due to "+
				"US and European policy. To be able to use up to 256-bit encryption "+
				"you have to replace your security jar files, that are limited to "+
				"128-bit. To replace the limited security jar files, you first have "+
				"to locate them, then erase them and them copy the unlimited jarfiles "+
				"from this application. You will do that in the next step.";
		JTextArea info = createInfoJTextArea("Arial", true, fm.getFontSizeForCorrectOP(11, 13, 11));
		info.setText(info_String);
		jpanel_step_1.add(info).setBounds(15, 5, 490, 90);
		
	}
	
	private void createStep1(){
		JLabel step1_1 = new JLabel();
		step1_1.setFont(smallTitleFont);
		step1_1.setText("In Windows OS:");
		jpanel_step_1.add(step1_1).setBounds(15, 100, 520, 30);
		
		JLabel step1_2 = new JLabel();
		step1_2.setFont(textFont);
		step1_2.setText("In windos it might be located at: C:\\Program Files\\Java\\jre8\\lib\\security");
		jpanel_step_1.add(step1_2).setBounds(25, 120, 520, 30);
	}
	
	private void createStep2(){
		JLabel step2_1 = new JLabel();
		step2_1.setFont(smallTitleFont);
		step2_1.setText("In Linux OS:");
		jpanel_step_1.add(step2_1).setBounds(15, 155, 520, 30);		
		
		JLabel step2_2 = new JLabel();
		step2_2.setFont(textFont);
		step2_2.setText("In linux it might be located at: usr/Java/jre8/lib/security");
		jpanel_step_1.add(step2_2).setBounds(25, 175, 520, 30);
		
	}
	
	private void createStep3(){
		JLabel step3_1 = new JLabel();
		step3_1.setFont(smallTitleFont);
		step3_1.setText("In Apple OS (Mac):");
		jpanel_step_1.add(step3_1).setBounds(15, 210, 520, 30);		
		
		JLabel step3_2 = new JLabel();
		step3_2.setFont(textFont);
		step3_2.setText("On mac it might be located at: /Library/Java/Home/lib/security");
		jpanel_step_1.add(step3_2).setBounds(25, 230, 520, 30);
	}
	
	private JTextArea createInfoJTextArea(String font, boolean bold, int size){
		JTextArea jtextArea = new JTextArea();
		if(bold==true){
			jtextArea.setFont(new Font(font,Font.BOLD,size));
		}else{
			jtextArea.setFont(new Font(font,Font.PLAIN,size));
		}
		jtextArea.setLineWrap(true);
		jtextArea.setWrapStyleWord(true);
		jtextArea.setBackground(new Color(0,0,0,0));
		//descr_process.setBorder(BorderFactory.createEtchedBorder());
		jtextArea.setEditable(false);
		return jtextArea;
	}
	
	public JPanel getJPanel(){
		return this.jpanel_step_1;
	}
}
