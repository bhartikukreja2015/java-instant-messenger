package runner;

import java.util.ArrayList;

import jimPreferences.PreferencePoint;
import abstractionLayer.AccountSettings;

public class PrefTest {

	public static void main(String[] args) {
		PrefTest myTest = new PrefTest();
		myTest.doIt();
	}
	
	public void doIt() {
		PreferencePoint PP = new PreferencePoint();
		
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
		
		ArrayList<AccountSettings> theList = PP.getAllAccounts();
		
		for (AccountSettings as : theList) {
			System.out.println("" + as.getID());
			System.out.println(as.getUsername());
			System.out.println(as.getPassword());
			System.out.println(as.getAccountType());
		}
		
		PP.deleteAllAccounts();
		
		
	}

}
