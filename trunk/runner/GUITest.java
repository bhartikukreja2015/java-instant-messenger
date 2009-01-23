package runner;

import guiStuff.AccountSettingsWindow;
import guiStuff.BuddyListWindow;
import guiStuff.IMWindow;
import jimPreferences.PreferencePoint;
import abstractionLayer.AccountSettings;

public class GUITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUITest myTest = new GUITest();
		myTest.doIt();
	}

	public void doIt() {
		PreferencePoint PP = new PreferencePoint();
		
		PP.deleteAllAccounts();
		
		AccountSettings myAS = new AccountSettings();
		AccountSettings myAS2 = new AccountSettings();
		
		myAS.setID(PP.getNextAccountID());
		myAS.setUsername("user1");
		myAS.setPassword("pass1");
		myAS.setAccountType(AccountSettings.GoogleTalkAccount);
		
		PP.saveAccount(myAS);
		
		myAS2.setID(PP.getNextAccountID());
		myAS2.setUsername("user2");
		myAS2.setPassword("pass2");
		myAS2.setAccountType(AccountSettings.YahooAccount);
		
		PP.saveAccount(myAS2);
		
		@SuppressWarnings("unused")
		AccountSettingsWindow myWin = new AccountSettingsWindow();
		BuddyListWindow myWin2 = new BuddyListWindow();
		IMWindow myWin3 = new IMWindow();
	}
}
