package upperAbstractionLayer;

import gtalkStuff.GTalkAccount;

import java.util.ArrayList;

import yahooStuff.YahooAccount;

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
	
	private ArrayList<BuddyListChangeListener> theBLCL;
	private ArrayList<IMListener> theIml;
	
	public AccountManager() {
		theAccounts = new ArrayList<AbstractAccount>();
		theSettings = new ArrayList<AccountSettings>();
		theList = new BuddyList(true); // true = sort
		
		theBLCL = new ArrayList<BuddyListChangeListener>();
		theIml = new ArrayList<IMListener>();
	}
	
	public void loadEnabledAccounts(PreferencePoint pp) {
		ArrayList<AccountSettings> loadedSettings = pp.getAllAccounts();
		

		
		for (AccountSettings as : loadedSettings) {
			if (as.isEnabled()) {
				theSettings.add(as);
			}
		}
	}
	
	public void makeAccounts() {
		// this method should move all the loaded account settings into actual accounts.
		// right now, we only have Gtalk and Yahoo
		
		for (AccountSettings as : theSettings) {
			AbstractAccount theAccount = null;
			if (as.getAccountType().equals(AccountSettings.GoogleTalkAccount)) {
				theAccount = new GTalkAccount();
			} else if (as.getAccountType().equals(AccountSettings.YahooAccount)) {
				theAccount = new YahooAccount();
			}
			
			theAccount.setAccountSettings(as);
			theAccount.setListener(this);
			
			theAccounts.add(theAccount);
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
	
	public void setStatus(Buddy theStatus) {
		for (AbstractAccount aa : theAccounts) {
			aa.setStatus(theStatus);
		}
	}
	
	public void sendIM(IM theIM){
		theIM.theAccount.sendIM(theIM);
	}
	
	public void addBuddyListChangeListener(BuddyListChangeListener blcl) {
		theBLCL.add(blcl);
	}
	
	public BuddyList getBuddyList() { return theList; }
	
	public void addIMListener(IMListener iml) {
		theIml.add(iml);
	}
	
	public void buddyStatusChange(Buddy theBuddy, boolean firstTime) {
		if (firstTime) {
			// if alias is null, we need to see if we have a stored alias
			if (theBuddy.getAlias() == null) {
				PreferencePoint PP = new PreferencePoint();
				
			}
			
			theList.addBuddy(theBuddy);
		} else {
			theList.updateBuddy(theBuddy);
		}
		
		for (BuddyListChangeListener blcl : theBLCL) {
			blcl.BuddyListChange(theList);
		}
	}

	public void gotIM(IM theIM) {
		for (IMListener theL : theIml) {
			theL.gotIM(theIM);
		}
	}

	public void loggedIn(AbstractAccount theAccount) {
		
		
	}
	
}
