package upperAbstractionLayer;

import gtalkStuff.GTalkAccount;

import java.util.ArrayList;

import jimPreferences.PreferencePoint;

import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;

public class AccountManager implements IMEvents {
	private ArrayList<AbstractAccount> theAccounts;
	private ArrayList<AccountSettings> theSettings;
	private BuddyList theList;
	
	public AccountManager() {
		theAccounts = new ArrayList<AbstractAccount>();
		theSettings = new ArrayList<AccountSettings>();
		theList = new BuddyList();
	}
	
	public void loadEnabledAccounts(PreferencePoint pp) {
		ArrayList<AccountSettings> loadedSettings = pp.getAllAccounts();
		
		for (AccountSettings as : loadedSettings) {
			if (as.isEnabled()) {
				theSettings.add(as);
			}
		}
	}
	
	protected void makeAccounts() {
		// this method should move all the loaded account settings into actual accounts.
		// right now, we only have Gtalk
		
		for (AccountSettings as : theSettings) {
			if (as.getAccountType().equals(AccountSettings.GoogleTalkAccount)) {
				AbstractAccount theGoogleAccount = new GTalkAccount();
				theGoogleAccount.setAccountSettings(as);
				
				theGoogleAccount.setListener(this);
				
				theAccounts.add(theGoogleAccount);
			}
		}
	}
	
	public void connectAll() {
		for (AbstractAccount aa : theAccounts) {
			aa.connect();
		}
	}
	
	public void disconnectAll() {
		for (AbstractAccount aa : theAccounts) {
			aa.disconnect();
		}
	}

	public void buddyStatusChange(Buddy theBuddy, boolean firstTime) {
		if (firstTime) {
			theList.addBuddy(theBuddy);
			return;
		}
		
		theList.updateBuddy(theBuddy);
	}

	public void gotIM(IM theIM) {
		// TODO Auto-generated method stub
		
	}

	public void loggedIn(AbstractAccount theAccount) {
		// TODO Auto-generated method stub
		
	}
	
}