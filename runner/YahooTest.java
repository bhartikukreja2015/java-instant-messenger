package runner;

import guiStuff.BuddyListWindow;
import guiStuff.IMWindowManager;
import jimPreferences.PreferencePoint;
import upperAbstractionLayer.AccountManager;
import abstractionLayer.AccountSettings;

public class YahooTest {

	public static void main(String[] args) {
		YahooTest myTest = new YahooTest();
		myTest.doIt(args[0], args[1]);
	}

	
	public void doIt(String username, String password) {
		AccountManager myAM = new AccountManager();
		PreferencePoint pp = new PreferencePoint();
		
		AccountSettings myAS = new AccountSettings(username, password);
		myAS.setAccountType(AccountSettings.YahooAccount);
		myAS.setEnabled(true);
		myAS.setAlias("Yahoo Test");
		
		pp.deleteAllAccounts();
		pp.saveAccount(myAS);
		
		myAM.loadEnabledAccounts(pp);
		myAM.makeAccounts();
		
		myAM.connectAll();
		
		IMWindowManager myIMWM = new IMWindowManager(myAM);
		
		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
		
		
		
	}
}
