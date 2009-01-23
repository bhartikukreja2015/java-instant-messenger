package runner;

import guiStuff.AccountSettingsWindow;
import jimPreferences.PreferencePoint;
import abstractionLayer.AccountSettings;

public class AccountWindowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountWindowTest myTest = new AccountWindowTest();
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
		
		AccountSettingsWindow myWin = new AccountSettingsWindow();
	}
}
