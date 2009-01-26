package runner;

import guiStuff.BuddyListWindow;
import guiStuff.IMWindowManager;
import jimPreferences.PreferencePoint;
import upperAbstractionLayer.AccountManager;
import abstractionLayer.AccountSettings;

public class CombinedGtalkYahooTest {

	public static void main(String[] args) {
		CombinedGtalkYahooTest myTest = new CombinedGtalkYahooTest();
		myTest.doIt(args[0], args[1], args[2], args[3]);

	}

	public void doIt(String username1, String password1, String username2, String password2) {
		AccountManager myAM = new AccountManager();
		PreferencePoint pp = new PreferencePoint();
		pp.deleteAllAccounts();
		
		AccountSettings myAS = new AccountSettings(username1, password1);
		myAS.setAccountType(AccountSettings.GoogleTalkAccount);
		myAS.setEnabled(true);
		myAS.setAlias("GTalk Test");
		myAS.setID(pp.getNextAccountID());
		
		pp.saveAccount(myAS);
		
		AccountSettings myAS2 = new AccountSettings(username2, password2);
		myAS2.setAccountType(AccountSettings.YahooAccount);
		myAS2.setEnabled(true);
		myAS2.setAlias("Yahoo Test");
		
		myAS2.setID(pp.getNextAccountID());
		pp.saveAccount(myAS2);
		
		myAM.loadEnabledAccounts(pp);
		myAM.makeAccounts();
		
		myAM.connectAll();
		
		IMWindowManager myIMWM = new IMWindowManager(myAM);
		
		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
	}
	
}
