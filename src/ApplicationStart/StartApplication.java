package ApplicationStart;

import GUI_Main.MainFrameGui;
import SecureFileManager.SingleInstanceApplicationManager;
public class StartApplication {
	public static void main(String[] args) throws Exception{
		new SingleInstanceApplicationManager().startUpProcess();
		MainFrameGui main = new MainFrameGui();
		main.openMainFrameWindow();
	}	
}



