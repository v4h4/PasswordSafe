package GUI_Help;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI_Main.DialogGui;
import SecureFileManager.FileChoosers;

public class Help_GuideInstalPanels {
	private Help_InstallWindow install_window=null;
	private JPanel[] orderPanels = null;
	private int steps=5;	
	private JButton previousButton = null;
	private JButton nextButton=null;
	private FileChoosers fc =null;
	//private CriteraForNextButton cri_next = null;
	private Help_PanelStep_4 jp_step4 =null;
	private JButton finnishButton[]=null;
	private DialogGui dialog = null;
	/************************************/
	
	public Help_GuideInstalPanels(Help_InstallWindow install_window, DialogGui dialog){
		this.install_window=install_window;
		this.dialog=dialog;
		this.orderPanels = new JPanel[steps];
		this.finnishButton = new JButton[orderPanels.length];
		this.fc = new FileChoosers();
		//this.cri_next = new CriteraForNextButton(fc, dialog);
		createStepPanels();
		createPreviousButton();
		createCancelButton();
		createNextButton();
		createFinnishButton();
		/***********************/
		stepOneGui();
		stepTwoGui();
		stepThreeGui();
		stepFourGui();
		stepFiveOverViewGui();
	}
	
	private void createStepPanels(){
		JPanel[] orderViews = createOrderView();
		for(int i=0;i<steps;i++){
			JPanel orderPanel = new JPanel();
			orderPanel.setVisible(false);
			orderPanel.setName(""+steps);
			orderPanel.setLayout(null);
			orderPanel.setBorder(BorderFactory.createEtchedBorder());
			orderPanel.add(orderViews[i]).setBounds(30, 10, 520, 110);
			orderPanels[i]=orderPanel;
		}
	}

	private JPanel[] createOrderView(){
		JPanel[] panels = new JPanel[steps];
		for(int i=0;i<steps;i++){
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(BorderFactory.createEtchedBorder());
			panel.setPreferredSize(new Dimension(510, 150));
			int posX=15;
			for(int q=0;q<steps;q++){
				JLabel[] labels= createOrderViewNbrLabels(i);
				panel.add(labels[q]).setBounds(posX,10 , 90, 90);
				posX=posX+100;
			}
			panels[i]=panel;
		}
		return panels;
	}
	
	
	private JLabel[] createOrderViewNbrLabels(int panelIndex){
		JLabel[] labels = new JLabel[steps];
		for(int i=0;i<steps;i++){
			JLabel label = new JLabel();
			label.setBorder(BorderFactory.createEtchedBorder());
			label.setFont(new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(85, 110, 85)));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setText(""+(i+1));
			label.setName(""+i);
			if(panelIndex==i){
				label.setForeground(new Color(0,150,0));
			}
			labels[i]=label;
		}
		return labels;
	}
	
	private void createPreviousButton(){
		for(int i=0;i<orderPanels.length;i++){
			previousButton = new JButton("Previous");
			previousButton.setName(""+i);
			previousButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
					if(orderNbr==4){
						orderPanels[orderNbr].setVisible(false);
						orderPanels[orderNbr-2].setVisible(true);
						finnnish_button_handler(orderNbr-2);
					}
					else if(orderNbr>0){
						orderPanels[orderNbr].setVisible(false);
						orderPanels[orderNbr-1].setVisible(true);
						finnnish_button_handler(orderNbr-1);
					}
				}
			});
			orderPanels[i].add(previousButton).setBounds(10, 450, 100, 30);
		}
	}
	
	private void createCancelButton(){
		for(int i=0;i<orderPanels.length;i++){
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setName(""+i);
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
					orderPanels[orderNbr].setVisible(false);
					orderPanels[0].setVisible(true);
					install_window.hideInstallWindow();
				}
			});
			orderPanels[i].add(cancelButton).setBounds(120, 450, 100, 30);
		}
	}
	
	private int cnbIterator=0;
	private void createNextButton(){
		for(cnbIterator=0;cnbIterator<orderPanels.length;cnbIterator++){
			nextButton = new JButton("Next");
			nextButton.setName(""+cnbIterator);
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
					if(orderNbr==2 && nextButtonIsApproved(orderNbr)==false){
						orderPanels[orderNbr].setVisible(false);
						orderPanels[orderNbr+1].setVisible(true);
						jp_step4.updateInstructions();
						finnnish_button_handler(orderNbr+1);
					}
					else if(orderNbr==2 && nextButtonIsApproved(orderNbr)){
						orderPanels[orderNbr].setVisible(false);
						orderPanels[orderNbr+2].setVisible(true);
						finnnish_button_handler(orderNbr+2);
					}
					else if(orderNbr<4 && nextButtonIsApproved(orderNbr)){
						orderPanels[orderNbr].setVisible(false);
						orderPanels[orderNbr+1].setVisible(true);
						jp_step4.updateInstructions();
						finnnish_button_handler(orderNbr+1);
					}
				}
			});
			orderPanels[cnbIterator].add(nextButton).setBounds(360, 450, 100, 30);
		}
	}
	
	private void createFinnishButton(){
		
		for(int i=0;i<orderPanels.length;i++){
			finnishButton[i] = new JButton("Finnish");
			finnishButton[i].setName(""+i);
			if(i==0){
				finnishButton[i].setEnabled(false);
			}
			finnishButton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
//					if(orderNbr==4 && cri_next.nextButtonIsApproved(orderNbr)){
							orderPanels[orderNbr].setVisible(false);
							orderPanels[0].setVisible(true);
							install_window.hideInstallWindow();
//					}		
				}
			});
			orderPanels[i].add(finnishButton[i]).setBounds(470, 450, 100, 30);
		}
	}
	/********************************************************************************/
	/*****************************Main Step GUI**************************************/
	/********************************************************************************/
		
	private void stepOneGui(){
		JLabel title = new JLabel();
		title.setFont(new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(12, 18, 12)));
		title.setText("Locate the security folder in the libary direcotry in the JRE.");
		orderPanels[0].add(title).setBounds(30, 130, 520, 30);
		Help_PanelStep_1 jp_step1 = new Help_PanelStep_1();
		orderPanels[0].add(jp_step1.getJPanel()).setBounds(30, 170, 520, 270);
	}
	
	private void stepTwoGui(){
		JLabel title = new JLabel();
		title.setFont(new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(12, 18, 12)));
		title.setText("Locate the security folder in the libary direcotry in the JRE.");
		orderPanels[1].add(title).setBounds(30, 130, 520, 30);
		Help_PanelStep_2 jp_step2 = new Help_PanelStep_2(fc);
		orderPanels[1].add(jp_step2.getJPanel()).setBounds(30, 170, 520, 270);
	}

	private void stepThreeGui(){
		JLabel title = new JLabel();
		title.setFont(new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(12, 18, 12)));
		title.setText("Locate the security folder in the libary direcotry in the JRE.");
		orderPanels[2].add(title).setBounds(30, 130, 520, 30);
		Help_PanelStep_3 jp_step3 = new Help_PanelStep_3(fc);
		orderPanels[2].add(jp_step3.getJPanel()).setBounds(30, 170, 520, 270);
	
	}
	
	private void stepFourGui(){
		JLabel title = new JLabel();
		title.setFont(new Font("Arial", Font.BOLD, fc.getFontSizeForCorrectOP(12, 18, 12)));
		title.setText("If this solution did not work, do following.");
		orderPanels[3].add(title).setBounds(30, 130, 520, 30);
		jp_step4 = new Help_PanelStep_4(fc);
		orderPanels[3].add(jp_step4.getJPanel()).setBounds(30, 170, 520, 270);
	
	}

	private void stepFiveOverViewGui(){
		Help_PanelStep_5 jp_step5 = new Help_PanelStep_5();
		orderPanels[4].add(jp_step5.getJPanel()).setBounds(30, 170, 520, 270);
	}

	private void finnnish_button_handler(int i){
		if(i == 4){
			this.finnishButton[i].setEnabled(true);
		}else{
			this.finnishButton[i].setEnabled(false);
		}
	}
	
	private int getCurrentOrderPanel(){
		for(int i=0;i<orderPanels.length;i++){
			if(orderPanels[i].isVisible()==true){
				return i;
			}
		}
		return 0;
	}
	
	
	private boolean nextButtonIsApproved(int panelNbr){
		if(panelNbr == 0){
			return step_1_criteria();
		}
		else if(panelNbr == 1){
			return step_2_criteria();
		}
		else if(panelNbr == 2){
			return step_3_criteria();
		}
		else if(panelNbr == 3){
			return step_4_criteria();
		}
		else if(panelNbr == 4){
			return step_5_criteria();
		}
		else{
			dialog.dynamicErrorDialogWindow("Error in application folder", "There is an error in the application folder,\nSecurity jar files and/or folder: UnlimitedStrengthSecurity is missing!");
			return false;
		}
	}
	
	@SuppressWarnings("static-access")
	private boolean step_1_criteria(){
		byte[] local=fc.getByteArray_to_Unlimited_local_policy();
		byte[] export=fc.getByteArray_to_Unlimited_us_export_policy();
		if(local!=null && export!=null){
			return true;
		}
		dialog.dynamicErrorDialogWindow("Error in application folder", "Security folder (UnlimitedStrengthSecurity) and/or its files are missing");
		return false;
	}
	
	@SuppressWarnings("static-access")
	private boolean step_2_criteria(){
		byte[] un_local=fc.getByteArray_to_Unlimited_local_policy();
		byte[] un_export=fc.getByteArray_to_Unlimited_us_export_policy();
		String local=fc.getPath_to_target_local_policy();
		String export=fc.getPath_to_target_us_export_policy();
		if(local!=null && export!=null && un_local!=null && un_export!=null){
			return true;
		}
		dialog.dynamicErrorDialogWindow("Error in selected security files", "One or both of the required security files has not been selected!");
		return false;
	}
	
	@SuppressWarnings("static-access")
	private boolean step_3_criteria(){
		byte[] un_local=fc.getByteArray_to_Unlimited_local_policy();
		byte[] un_export=fc.getByteArray_to_Unlimited_us_export_policy();
		String local=fc.getPath_to_target_local_policy();
		String export=fc.getPath_to_target_us_export_policy();
		if(local!=null && export!=null && un_local!=null && un_export!=null){
			byte[] local_target=fc.filepath_2_byteArr(fc.getPath_to_target_local_policy());
			boolean target_unlimited_local_result=fc.byteArrayIsIdentical(local_target, un_local);
			byte[] export_target=fc.filepath_2_byteArr(fc.getPath_to_target_us_export_policy());
			boolean export_target_unlimited_result=fc.byteArrayIsIdentical(export_target, un_export);
			if(target_unlimited_local_result==true && export_target_unlimited_result==true){
				return true;
			}else{
				dialog.dynamicErrorDialogWindow("Error in application folder and/or selected files", "You have to manuelly fix this. Go to step 4. ");
			}
		}else{
			dialog.dynamicErrorDialogWindow("Error in application folder and/or selected files", "Security folder (UnlimitedStrengthSecurity) and/or its \nfiles are missing and/or the selected security jar files");	
		}
		return false;
	}
	
	private boolean step_4_criteria(){
		
		return false;
	}
	
	private boolean step_5_criteria(){
		
		return false;
	}
	
	
	public JPanel[] getJPanels(){
		orderPanels[0].setVisible(true);
		return this.orderPanels;
	}
	
	
	
	
}
