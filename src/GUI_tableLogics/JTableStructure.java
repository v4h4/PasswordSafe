package GUI_tableLogics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.luan.LUANObject;

import GUI_Main.DialogGui;
import SecureFileManager.FileManager;

public class JTableStructure {
	private JTablePopupDataModification tpdm =null;
	private JTablePopupMenu table_popup=null;
	private DialogGui dialog = null;
	private JTable passwordTable = null;
	private JScrollPane passwordTableScroll=null;
	private String [] tableColumn={"id","Organisation","Email","Username","Password","Date","Time"};
	private String tableData[][]=null;
	private LUANObject luanTablePassword =null;
	private int id=0;
	private Component mouseComponenet=null;
	private FileManager fm =null;
	private int selectedRow=-1;
	public JTableStructure(DialogGui dialog, FileManager fm){
		this.fm=fm;
		this.dialog=dialog;
		createTableData();
		createTabele();
		this.tpdm= new JTablePopupDataModification(this);
		this.table_popup = new JTablePopupMenu(this, tpdm, dialog);
	}
	
	private void createTableData(){
		this.tableData = new String[23][tableColumn.length];
		for(int y =0;y<tableData.length;y++){
			for(int x=0;x<tableColumn.length;x++){
				tableData[y][x]="";
			}
		}
	}
	
	
	private void createTabele(){
		this.passwordTable=new JTable(tableData,tableColumn){
			private static final long serialVersionUID = 1L;
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    passwordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//only one row cal be selected
	    passwordTable.setRowHeight(25);
	   
	    passwordTable.getColumnModel().getColumn(0).setPreferredWidth(50);//sets fileName column in 200 pixels in size in the withd
		passwordTable.getColumnModel().getColumn(1).setPreferredWidth(200);//sets fileType column in 80 pixels in size in the withd
		passwordTable.getColumnModel().getColumn(2).setPreferredWidth(200);//sets fileSize column in 80 pixels in size in the withd
		passwordTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		passwordTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		passwordTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		passwordTable.getColumnModel().getColumn(6).setPreferredWidth(70);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		passwordTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);//centers cellvalue fileType
		passwordTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);//centers cellvalue fileSize
		passwordTable.setPreferredScrollableViewportSize(new Dimension(400,400));
		passwordTable.getTableHeader().setReorderingAllowed(false);
		passwordTable.setFillsViewportHeight(true);
		passwordTable.setBorder(BorderFactory.createEtchedBorder());
		passwordTable.getColumnModel().getColumn(4).setCellRenderer(new PasswordCellRenderer());
		//passwordTable.set

		for(int y=0;y<tableData[0].length;y++){
	    	for(int x=0;x<tableColumn.length;x++){
		    	passwordTable.isCellEditable(y, x);
		    }
	    }
		passwordTable.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent mouseEvent) {
				 if(SwingUtilities.isRightMouseButton(mouseEvent)){
					    selectedRow=passwordTable.getSelectedRow(); 
					    
					    if(selectedRow>=0){
					    	 table_popup.getPopupMenu().show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
							 mouseComponenet = mouseEvent.getComponent();
						
					     }else{
								dialog.dynamicErrorDialogWindow("Selection Error", "No table row has been selected!");
						 }
				 }
			}
			public void mousePressed(MouseEvent mouseEvent) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {}
    	});
		passwordTableScroll = new JScrollPane(passwordTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		passwordTableScroll.setBorder(BorderFactory.createEtchedBorder());
	}
		
	private void addRowToTableData(String org, String email, String usr, String pwd){
		int emptyRow=emptyRowNumberAt();
		if(emptyRow>-1){
			addValuesToRow(emptyRow,tableData, org, email, usr, pwd);
		}else{
			String[][] tempdata = new String[tableData.length+1][tableColumn.length];
			for(int y=0;y<tableData.length;y++){
				for(int x =0; x<tableColumn.length;x++){
					tempdata[(y+1)][x]=tableData[y][x];
				}
			}
			addValuesToRow(0,tempdata, org, email, usr, pwd);
		}
		try{
			if(luanTablePassword.containsStringTwoDeminsionalArray("tableData")){
				luanTablePassword.removeStringTwoDimensionalArray("tableData");
			}
				luanTablePassword.put("tableData", tableData);
				fm.updatePasswordTable(luanTablePassword);
		}catch(Exception ex){
			ex.printStackTrace();
			dialog.dynamicErrorDialogWindow("Error Saving Passwords", "Could not save password to file\nError message: "+ex.getMessage());
		}
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
	
	private int emptyRowNumberAt(){
		for(int y=0;y<tableData.length;y++){
			if(stringIsInteger(tableData[y][0])==false){
				return y;
			}
		}
		return -1;
	}
	
	private void addValuesToRow(int row,String[][] tempdata,String org, String email, String usr, String pwd){
		tableData=tempdata;
		tableData[row][0]=getUniqueID();
		tableData[row][1]=org;
		tableData[row][2]=email;
		tableData[row][3]=usr;
		tableData[row][4]=pwd;
		tableData[row][5]=getCurrentDate();
		tableData[row][6]=getCurrentTime();
		
	}
	
	public void deleteValuesFromRow(int row){
		for(int x=0;x<tableColumn.length;x++){
			tableData[row][x]="";
		}
		updateEntireTable();
	}
	
	private String getUniqueID(){
		
		for(int y=0;y<tableData.length;y++){
			if(tableData[y][0]!=null && stringIsInteger(tableData[y][0]) && (id-1)<Integer.parseInt(tableData[y][0])){
				id=Integer.parseInt(tableData[y][0])+1;;
			}
		}
		return ""+id;
	}
	
	public boolean updateOneRowAtTable(String org, String email, String usr, String pwd){
		addRowToTableData(org, email, usr, pwd);
		updateEntireTable();
		return true;
	}
	
	public void updateEntireTableFromFile(LUANObject luan){
		try{
			this.luanTablePassword=luan;
			if(luan !=null && luan.containsStringTwoDeminsionalArray("tableData")==true){
				tableData=luan.getStringTwoDimensionalArray("tableData");
				DefaultTableModel standard = new DefaultTableModel(tableData,tableColumn);
				standard.fireTableDataChanged();
				passwordTable.setModel(standard);
				passwordTable.getColumnModel().getColumn(4).setCellRenderer(new PasswordCellRenderer());
			}else{
				createTableData();
				updateEntireTable();
			}
		}catch(Exception ex){
			dialog.setMainFrameVisble(false);
			dialog.dynamicErrorDialogWindow("Empty PasswordSafe", "Your PasswordSafe is empty!");
			dialog.setMainFrameVisble(true);
		}
	}
	
	public void updateEntireTable(){
		try{
			DefaultTableModel standard = new DefaultTableModel(tableData,tableColumn);
			standard.fireTableDataChanged();
			passwordTable.setModel(standard);
			if(luanTablePassword.containsStringTwoDeminsionalArray("tableData")){
				luanTablePassword.removeStringTwoDimensionalArray("tableData");
			}
			luanTablePassword.put("tableData", tableData);
			fm.updatePasswordTable(luanTablePassword);	
			if(tableData.length==0 || tableData.length==1 && tableData[0][0].trim().equals("")){
				createTableData();
				updateEntireTable();
			}
		}catch(Exception ex){
			dialog.dynamicErrorDialogWindow("Could not open file", "Could not open and/or decrypt passwordTable\nError message: "+ex.getMessage());
		}
		passwordTable.getColumnModel().getColumn(4).setCellRenderer(new PasswordCellRenderer());
	}
	
	public String[][] deleteAllEmptyRows(String[][] tableData){
		if(tableData!=null && tableData.length>0 && tableColumn.length>0){
			int validRows=0;
			for(int y=0;y<tableData.length;y++){
				if(stringIsInteger(tableData[y][0])){
					validRows++;
				}
			}
			if(validRows>0){
				String[][] temp = new String[validRows][tableColumn.length]; 
				int xPos=0,yPos=0;
				for(int y=0;y<tableData.length && y<temp.length;y++){
					if(stringIsInteger(tableData[y][0])){
						xPos=0;
						for(int x=0;x<tableColumn.length;x++){
							if(yPos<temp.length && xPos<tableColumn.length){
								temp[yPos][xPos]=tableData[y][x];
								xPos++;
							}
						}
						yPos++;
					}
				}
				tableData=temp;
			}
		}
		return tableData;
	}
	
	public boolean stringIsInteger(String ineteger){
		try{
			@SuppressWarnings("unused")
			int test = Integer.parseInt(ineteger);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	
	public JTable getPasswordTable(){
		return this.passwordTable;
	}
	
	public JScrollPane getPasswordTableScroll(){
		return this.passwordTableScroll;
	}
	
	public String[][] getTableData(){
		return this.tableData;
	}
	
	public void setTableData(String[][] tableData){
		this.tableData=tableData;
	}
	
	public String[] getTableColumn(){
		return this.tableColumn;
	}
	
	public Component getMouseComponent(){
		return this.mouseComponenet;
	}
	
	public int getSelectedRow(){
		return this.selectedRow;
	}
	
	public void setLuanObectToNull(){
		this.luanTablePassword=null;
		createTableData();
	}
}
