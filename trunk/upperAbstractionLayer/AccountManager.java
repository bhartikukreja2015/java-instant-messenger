package upperAbstractionLayer;

import gtalkStuff.GTalkAccount;

import java.util.ArrayList;

import msnStuff.MSNAccount;

import yahooStuff.YahooAccount;

import jimPreferences.PreferencePoint;

import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;

public class AccountManager implements IMEvents, AliasChangeEvent {
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
	
	public void clearAccounts() {
		theSettings = new ArrayList<AccountSettings>();
		this.disconnectAll();
		theAccounts = new ArrayList<AbstractAccount>();
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
		// right now, we only have Gtalk, Yahoo, and MSN
		
		for (AccountSettings as : theSettings) {
			AbstractAccount theAccount = null;
			if (as.getAccountType().equals(AccountSettings.GoogleTalkAccount)) {
				theAccount = new GTalkAccount();
			} else if (as.getAccountType().equals(AccountSettings.YahooAccount)) {
				theAccount = new YahooAccount();
			} else if (as.getAccountType().equals(AccountSettings.MSNAccount)) {
				theAccount = new MSNAccount();
			} else {
				// got unknown account type...
				// TODO is there a better way to handle this?
				System.out.println("Got unknown account type, don't know what to do: " + as.getAccountType());
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
			theList.accountDisconnected(aa);
		}
		
		for (BuddyListChangeListener blcl : theBLCL) {
			blcl.BuddyListChange(theList);
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

	public void buddyDeleted(Buddy theBuddy) { theList.removeBuddy(theBuddy); }

	public void buddyAliasChanged(Buddy b) {
		theList.updateBuddy(b);
		for (BuddyListChangeListener blcl : theBLCL) {
			blcl.BuddyListChange(theList);
		}
	}

	public void loginError(AbstractAccount theAccount) {
		// TODO Auto-generated method stub
		
	}
	
}
