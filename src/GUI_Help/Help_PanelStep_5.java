package GUI_Help;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Help_PanelStep_5 {
	private JPanel jpanel_step_1 = null;
	public Help_PanelStep_5(){
		createJPanelStep1();
	}
	
	private void createJPanelStep1(){
		jpanel_step_1 = new JPanel();
		jpanel_step_1.setLayout(null);
		jpanel_step_1.setBorder(BorderFactory.createEtchedBorder());
		jpanel_step_1.setVisible(true);
		addJLabelMissionToPanel();
		addJLabelAccomplishedToPanel();
	}
	
	private void addJLabelMissionToPanel(){
		JLabel mission_label = new JLabel();	
		mission_label.setFont(new Font("Arial",Font.BOLD,80));
		//mission_label.setBorder(BorderFactory.createEtchedBorder());
		mission_label.setHorizontalAlignment(SwingConstants.CENTER);
		mission_label.setText("Mission");
		jpanel_step_1.add(mission_label).setBounds(15,40,500,100);
	}
	
	private void addJLabelAccomplishedToPanel(){
		JLabel accomplished_label = new JLabel();	
		accomplished_label.setFont(new Font("Arial",Font.BOLD,60));
		//accomplished_label.setBorder(BorderFactory.createEtchedBorder());
		accomplished_label.setHorizontalAlignment(SwingConstants.CENTER);
		accomplished_label.setText("Accomplished");
		jpanel_step_1.add(accomplished_label).setBounds(15,140,500,100);
	}

	
	public JPanel getJPanel(){
		return this.jpanel_step_1;
	}
}
