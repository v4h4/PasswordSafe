package GUI_Help;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import SecureFileManager.FileManager;

public class InstructionsWindow {
	private JPanel[] instructionPanels = null;
	private int slides=6;	
	private JFrame instructionFrame = null;
	private FileManager fm = null;
	public InstructionsWindow(){
		fm = new FileManager();
		this.instructionPanels = new JPanel[slides];
		createInstructionFrame();
		createStepPanels();
		createCancelButton();
		createPreviousButton();
		createNextButton();
		createFinnishButton();
		createInstructionPanel_1();
		createInstructionPanel_2();
		createInstructionPanel_3();
		createInstructionPanel_4();
		createInstructionPanel_5();
		createInstructionPanel_6();
		instructionFrameCloseListener();
	}
	
	private void createInstructionFrame(){
		this.instructionFrame= new JFrame();
		instructionFrame.setIconImage(fm.fileFromJar2Image("locked.png"));
		instructionFrame.setTitle("PasswordSafe 1.0");
		instructionFrame.setVisible(false);
		instructionFrame.setSize(new Dimension(665, 540));//605, 540
		instructionFrame.setLayout(null);
		instructionFrame.setLocationRelativeTo(null);
		instructionFrame.setResizable(false);
		instructionFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		instructionFrame.setLocation(200, 10);
		
	}
	
	private void createStepPanels(){
		JPanel[] orderViews = createOrderView();
		for(int i=0;i<orderViews.length;i++){
			JPanel orderPanel = new JPanel();
			orderPanel.setLayout(null);
			orderPanel.setBorder(BorderFactory.createEtchedBorder());
			orderPanel.add(orderViews[i]).setBounds(10, 10, (slides*(90+10))+20, 110);
			orderPanel.setVisible(false);
			instructionPanels[i]=orderPanel;
			instructionFrame.add(instructionPanels[i]).setBounds(10, 10, 640, 490);
		}
		instructionPanels[0].setVisible(true);
	}
	
	private JPanel[] createOrderView(){
		JPanel[] panels = new JPanel[slides];
		for(int i=0;i<slides;i++){
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(BorderFactory.createEtchedBorder());
			panel.setPreferredSize(new Dimension((slides*(90+10))+20, 150));
			int posX=15;
			for(int q=0;q<slides;q++){
				JLabel[] labels= createOrderViewNbrLabels(i);
				panel.add(labels[q]).setBounds(posX,10 , 90, 90);
				posX=posX+100;
			}
			panels[i]=panel;
		}
		return panels;
	}
	
	private JLabel[] createOrderViewNbrLabels(int panelIndex){
		JLabel[] labels = new JLabel[slides];
		for(int i=0;i<slides;i++){
			JLabel label = new JLabel();
			label.setBorder(BorderFactory.createEtchedBorder());
			label.setFont(new Font("Arial", Font.BOLD, fm.getFontSizeForCorrectOP(85, 110, 85)));
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
		for(int i=0;i<instructionPanels.length;i++){
			JButton previousButton = new JButton("Previous");
			previousButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
					if(orderNbr>0 && orderNbr<slides){
						instructionPanels[orderNbr].setVisible(false);
						instructionPanels[orderNbr-1].setVisible(true);
					}
				}
			});
			instructionPanels[i].add(previousButton).setBounds(10, 450, 100, 30);
		}
	}
	
	private void createCancelButton(){
		for(int i=0;i<instructionPanels.length;i++){
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					hideInstructionFrame();
				}
			});
			instructionPanels[i].add(cancelButton).setBounds(120, 450, 100, 30);
		}
	}
	
	private void createNextButton(){
		for(int i=0;i<instructionPanels.length;i++){
			JButton nextButton = new JButton("Next");
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int orderNbr=getCurrentOrderPanel();
					if(orderNbr>=0 && orderNbr<(slides-1)){
						instructionPanels[orderNbr].setVisible(false);
						instructionPanels[orderNbr+1].setVisible(true);
					}	
				}
			});
			instructionPanels[i].add(nextButton).setBounds(420, 450, 100, 30);
		}
	}
	
	private void createFinnishButton(){
		for(int i=0;i<instructionPanels.length;i++){
			JButton buttonFinnish =new JButton("Finnish");
			buttonFinnish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					hideInstructionFrame();
				}
			});
			instructionPanels[i].add(buttonFinnish).setBounds(530, 450, 100, 30);
		}
	}
	
	private int getCurrentOrderPanel(){
		for(int i=0;i<instructionPanels.length;i++){
			if(instructionPanels[i].isVisible()==true){
				return i;
			}
		}
		return 0;
	}

	
	public void showInstructions(){
		instructionFrame.setVisible(true);
	}
	
	/*******************************************************************************/
	
	private void createInstructionPanel_1(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"To create new user, you have to enter:"+
				"\n\n* encryption algorithma "+
				"\n\n* salt-key, "+
				"\n\n* IV-key"+
				"\n\n* Encryption Key "+
				"\n\n* Password iterations, "+
				"\n\n* key-size";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(10, 12, 10), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_1.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[0].add(panel).setBounds(10,130,620,310);
	}
	
	private void createInstructionPanel_2(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"To delete the user and its content, you can either "+
				"erase it by entering the diffrent passwords and "+
				"selectable choices and press delete or just delete it "+
				"from the application folder";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(10, 12, 10), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_2.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[1].add(panel).setBounds(10,130,620,310);
	}

	private void createInstructionPanel_3(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"To login you have to enter the correct: "+
				"\n\n* encryption algorithma "+
				"\n\n* salt-key, "+
				"\n\n* IV-key"+
				"\n\n* Encryption Key "+
				"\n\n* Password iterations, "+
				"\n\n* key-size";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(10, 12, 10), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_3.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[2].add(panel).setBounds(10,130,620,310);
	}

	private void createInstructionPanel_4(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"Password Options:"+
				"\n\n* Add new password"+
				"\n\n* Show password"+
				"\n\n* Change password"+
				"\n\n* Delete password";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(11, 13, 11), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_4.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[3].add(panel).setBounds(10,130,620,310);
	}
	
	private void createInstructionPanel_5(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"Copy Options:"+
				"\n\n* Copy password"+
				"\n\n* Copy username"+
				"\n\n* Copy email";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(11, 13, 11), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_5.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[4].add(panel).setBounds(10,130,620,310);
	}

	private void createInstructionPanel_6(){
		JPanel panel = createGeneralInstructionPanel();
		String textInstruction=
				"Sorting Options:"+
				"\n\n* Sort by id"+
				"\n\n* Sort by organisation"+
				"\n\n* Sort by email"+
				"\n\n* Sort by password"+
				"\n\n* Sort by date"+
				"\n\n* Sort by time";
		
		JTextArea instructionArea = createInstructionJTextArea("Arial", true, fm.getFontSizeForCorrectOP(11, 13, 11), textInstruction);
		panel.add(instructionArea).setBounds(520, 15, 90, 280);
		JLabel imageLabel = new JLabel();//
		imageLabel.setSize(new Dimension(500, 280));
		ImageIcon image =fm.getImageIcon("instruction_6.PNG",imageLabel.getWidth(),imageLabel.getHeight());
		imageLabel.setIcon(image);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageLabel).setLocation(10, 15);
		instructionPanels[5].add(panel).setBounds(10,130,620,310);
	}

	private JTextArea createInstructionJTextArea(String font, boolean bold, int size, String textInstructions){
		JTextArea jtextArea = new JTextArea();
		if(bold==true){
			jtextArea.setFont(new Font(font,Font.BOLD,size));
		}else{
			jtextArea.setFont(new Font(font,Font.PLAIN,size));
		}
		jtextArea.setLineWrap(true);
		jtextArea.setWrapStyleWord(true);
		jtextArea.setBackground(new Color(0,0,0,0));
		//jtextArea.setBorder(BorderFactory.createEtchedBorder());
		jtextArea.setEnabled(false);
		jtextArea.setDisabledTextColor(Color.BLACK);
		jtextArea.setText(textInstructions);
		return jtextArea;
	}

	private JPanel createGeneralInstructionPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setVisible(true);
		return panel;
	}

	private void instructionFrameCloseListener(){
		instructionFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				hideInstructionFrame();
			}
		});
	}
	
	private void hideInstructionFrame(){
		int orderNbr=getCurrentOrderPanel();
		instructionPanels[orderNbr].setVisible(false);
		instructionPanels[0].setVisible(true);
		instructionFrame.setVisible(false);
	}




























}
