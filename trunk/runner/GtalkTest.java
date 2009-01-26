package runner;

import guiStuff.BuddyListWindow;
import guiStuff.IMWindowManager;
import jimPreferences.PreferencePoint;
import upperAbstractionLayer.AccountManager;
import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;

public class GtalkTest {
	AbstractAccount myGtalk;
	AccountSettings myAS;
	
	public static void main(String[] args) {
		GtalkTest myTest = new GtalkTest();
		myTest.doIt(args[0], args[1]);
	}
	
	public void doIt(String username, String password) {
		AccountManager myAM = new AccountManager();
		PreferencePoint pp = new PreferencePoint();
		
		AccountSettings myAS = new AccountSettings(username, password);
		myAS.setAccountType(AccountSettings.GoogleTalkAccount);
		myAS.setEnabled(true);
		myAS.setAlias("GTalk Test");
		
		pp.deleteAllAccounts();
		pp.saveAccount(myAS);
		
		myAM.loadEnabledAccounts(pp);
		myAM.makeAccounts();
		
		myAM.connectAll();
		
		IMWindowManager myIMWM = new IMWindowManager(myAM);
		
		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
		myBLW.getModel().setShowOffline(false);
		
		
		
	}

	
	
	
}
