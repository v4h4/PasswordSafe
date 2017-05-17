package GUI_tableLogics;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import GUI_Main.DialogGui;

public class JTablePopupMenu {
	private JTableSorting t_sort=null;
	private JPopupMenu rightclickMenu = null; 
	private JTablePopupDataModification tpdm=null;
	private JTableStructure ts=null;
	private JMenu submenu_sort=null;
	private JMenu submenu_pwdOpt=null;
	private JMenu submenu_copyOpt=null;
	private DialogGui dialog=null;
	
	public JTablePopupMenu(JTableStructure ts, JTablePopupDataModification tpdm, DialogGui dialog){
		this.ts=ts;
		this.tpdm=tpdm;
		this.t_sort = new JTableSorting();
		this.dialog=dialog;
		createRightClickPopupMenu();
	}
	
	private void createRightClickPopupMenu(){
		 this.rightclickMenu = new JPopupMenu("Popup");
		 this.submenu_sort = new JMenu("Sorting Table");
		 this.submenu_pwdOpt = new JMenu("Password Options");
		 this.submenu_copyOpt = new JMenu("Copy Options");
		 copyPassword_ToCopySubMenu();
		 copyUsername_ToCopySubMenu();
		 copyEmail_ToCopySubMenu();
		 add_CreatePasswordItem_ToPwdSubMenu();
		 add_ChangePasswordItem_ToPwdSubMenu();
		 add_ShowPasswordItem_ToPwdSubMenu();
		 add_DeletePasswordItem_ToPwdSubMenu();
		 add_sortingItemByID_ToSortingSubMenu();
		 add_sortingItemByOrg_ToSortingSubMenu();
		 add_sortingItemByEmail_ToSortingSubMenu();
		 add_sortingItemByUsr_ToSortingSubMenu();
		 add_sortingItemByDate_ToSortingSubMenu();
		 add_sortingItemByTime_ToSortingSubMenu();
		 addUpdateItemToMenu();
		 rightclickMenu.add(submenu_copyOpt);
		 rightclickMenu.add(submenu_pwdOpt);
		 rightclickMenu.add(submenu_sort);	
	}
	
	private void addUpdateItemToMenu(){
		 JMenuItem updateTableItem = new JMenuItem("Update");
		 rightclickMenu.add(updateTableItem);
		 updateTableItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void copyPassword_ToCopySubMenu(){
		JMenuItem copyPasswordItem = new JMenuItem("Copy Password");
		 submenu_copyOpt.add(copyPasswordItem );
		 copyPasswordItem .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						String get= ts.getTableData()[row][4];
						StringSelection selec= new StringSelection(get);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(selec, selec);
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}	
					
				}catch(Exception ex){
					//val.dynamicErrorDialogWindow("Error "+ex.getCause(), ex.getMessage());
				}
			}
		 });
	}
	
	private void copyUsername_ToCopySubMenu(){
		JMenuItem copyUsernameItem = new JMenuItem("Copy Username");
		 submenu_copyOpt.add(copyUsernameItem );
		 copyUsernameItem .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						String get= ts.getTableData()[row][3];
						StringSelection selec= new StringSelection(get);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(selec, selec);
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}	
					
				}catch(Exception ex){
					//val.dynamicErrorDialogWindow("Error "+ex.getCause(), ex.getMessage());
				}
			}
		 });
	}
	
	private void copyEmail_ToCopySubMenu(){
		JMenuItem copyEmailItem = new JMenuItem("Copy Email");
		 submenu_copyOpt.add(copyEmailItem );
		 copyEmailItem .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						String get= ts.getTableData()[row][2];
						StringSelection selec= new StringSelection(get);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(selec, selec);
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}	
					
				}catch(Exception ex){
					//val.dynamicErrorDialogWindow("Error "+ex.getCause(), ex.getMessage());
				}
			}
		 });
	}
	
	private void add_CreatePasswordItem_ToPwdSubMenu(){
		JMenuItem addPwdItem = new JMenuItem("Add New Password");
		 submenu_pwdOpt.add(addPwdItem);
		 addPwdItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{	
					tpdm.showTableDialog_addPwd();
				}catch(Exception ex){
					//val.dynamicErrorDialogWindow("Error "+ex.getCause(), ex.getMessage());
					//hidePopupMenu();
				}
			}
		 });
	}
	
	private void add_ChangePasswordItem_ToPwdSubMenu(){
		JMenuItem changePwdItem = new JMenuItem("Change Password");
		 submenu_pwdOpt.add(changePwdItem);
		 changePwdItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						tpdm.showTableDialog_changePwd(row);
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_ShowPasswordItem_ToPwdSubMenu(){
		JMenuItem showPwdItem = new JMenuItem("Show Password");
		 submenu_pwdOpt.add(showPwdItem);
		 showPwdItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{	
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						tpdm.showTableDialog_showPwd(row);
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}catch(Exception ex){
					//val.dynamicErrorDialogWindow("Error "+ex.getCause(), ex.getMessage());
					//hidePopupMenu();
				}
			}
		 });
	}

	private void add_DeletePasswordItem_ToPwdSubMenu(){
		JMenuItem deletePwdItem = new JMenuItem("Delete Password");
		 submenu_pwdOpt.add(deletePwdItem);
		 deletePwdItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row=ts.getPasswordTable().getSelectedRow();
					if(!ts.getTableData()[row][0].trim().equals("")){
						boolean delete=dialog.dynamicConfirmationDialog("Deleting a Password Confirmation", "Are you sure you want to delete this password \nfrom the: "+ts.getTableData()[row][1]+" organisation");
						if(delete==true){
							delete=dialog.dynamicConfirmationDialog("Deleting a Password Confirmation", "Are you reallysure, this action is permanent!!");
							if(delete==true){
								ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
								ts.deleteValuesFromRow(row);
								ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
								ts.updateEntireTable();
							}
						}
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByID_ToSortingSubMenu(){
		JMenuItem sortByIdItem = new JMenuItem("Sort Table by ID");
		 submenu_sort.add(sortByIdItem);
		 sortByIdItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByID(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByOrg_ToSortingSubMenu(){
		JMenuItem sortByOrgItem = new JMenuItem("Sort Table by Org");
		 submenu_sort.add(sortByOrgItem);
		 sortByOrgItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByOrg(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByEmail_ToSortingSubMenu(){
		JMenuItem sortByEmailItem = new JMenuItem("Sort Table by Email");
		 submenu_sort.add(sortByEmailItem);
		 sortByEmailItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByEmail(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByUsr_ToSortingSubMenu(){
		JMenuItem sortByUsrItem = new JMenuItem("Sort Table by Username");
		 submenu_sort.add(sortByUsrItem);
		 sortByUsrItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByUsername(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByDate_ToSortingSubMenu(){
		JMenuItem sortByDateItem = new JMenuItem("Sort Table by Date");
		 submenu_sort.add(sortByDateItem);
		 sortByDateItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByDate(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	private void add_sortingItemByTime_ToSortingSubMenu(){
		JMenuItem sortByTimeItem = new JMenuItem("Sort Table by Time");
		 submenu_sort.add(sortByTimeItem);
		 sortByTimeItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!ts.getTableData()[ts.getSelectedRow()][0].trim().equals("")){
						ts.setTableData(ts.deleteAllEmptyRows(ts.getTableData()));
						ts.setTableData(t_sort.sortByTime(ts.getTableData()));
						ts.updateEntireTable();
					}else{
						dialog.dynamicErrorDialogWindow("Selection Error", "Empty row was selected!");
					}
				}
		 });
	}
	
	public JPopupMenu getPopupMenu(){
		return this.rightclickMenu;
	}
	
}
