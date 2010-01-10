package org.bubuntux.jim.upperAbstractionLayer;

import java.util.ArrayList;

import org.bubuntux.jim.abstractionLayer.AbstractAccount;
import org.bubuntux.jim.abstractionLayer.AccountSettings;
import org.bubuntux.jim.abstractionLayer.Buddy;
import org.bubuntux.jim.abstractionLayer.BuddyList;
import org.bubuntux.jim.abstractionLayer.IM;
import org.bubuntux.jim.abstractionLayer.IMEvents;
import org.bubuntux.jim.abstractionLayer.Status;
import org.bubuntux.jim.preferences.PreferencePoint;
import org.bubuntux.jim.protocol.gtalk.GTalkAccount;
import org.bubuntux.jim.protocol.msn.MSNAccount;
import org.bubuntux.jim.protocol.yahoo.YahooAccount;

public class AccountManager implements IMEvents, AliasChangeEvent {
	private ArrayList<AbstractAccount> theAccounts;
	private ArrayList<AccountSettings> theSettings;
	private BuddyList theList;

	private ArrayList<BuddyListChangeListener> theBLCL;
	private ArrayList<IMListener> theIml;
	private ArrayList<StatusChangeListener> theSCL;
	private ArrayList<AccountConnectionListener> theACL;

	public AccountManager() {
		theAccounts = new ArrayList<AbstractAccount>();
		theSettings = new ArrayList<AccountSettings>();
		theList = new BuddyList(true); // true = sort

		theBLCL = new ArrayList<BuddyListChangeListener>();
		theIml = new ArrayList<IMListener>();
		theSCL = new ArrayList<StatusChangeListener>();
		theACL = new ArrayList<AccountConnectionListener>();
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
				connectWithTemplate(as);
			}
		}
	}

	public void disconnectAll() {
		for (AccountSettings as : theSettings) {
			disconnectAccount(as);
		}
	}

	public AbstractAccount getAccount(AccountSettings as) {
		for (AbstractAccount aa : theAccounts) {
			if (aa.getAccountSettings().getID() == as.getID()) {
				return aa;
			}
		}
		return null;
	}

	public void connectWithTemplate(AccountSettings as) {
		// first, disconnect the account
		disconnectAccount(as);

		// now, add the settings to our settings list...
		theSettings.add(as);

		// next, create the account...
		AbstractAccount theAccount = null;
		if (as.getAccountType().equals(AccountSettings.GoogleTalkAccount)) {
			theAccount = new GTalkAccount();
			;
		} else if (as.getAccountType().equals(AccountSettings.YahooAccount)) {
			theAccount = new YahooAccount();
		} else if (as.getAccountType().equals(AccountSettings.MSNAccount)) {
			theAccount = new MSNAccount();
		} else {
			// got unknown account type...
			// TODO is there a better way to handle this?
			System.out
					.println("Got unknown account type, don't know what to do: "
							+ as.getAccountType());
		}

		theAccount.setAccountSettings(as);
		theAccount.setListener(this);

		theAccounts.add(theAccount);

		// connect the account
		for (AccountConnectionListener acl : theACL) {
			acl.startingConnection(theAccount);
		}

		theAccount.connect();
	}

	public void disconnectAccount(AccountSettings as) {
		// first, remove the account settings if we already have it
		int i = 0;
		while (i != theSettings.size()) {
			if (theSettings.get(i).getID() == as.getID()) {
				theSettings.remove(i);
				i = theSettings.size();
			} else {
				i++;
			}
		}

		// now, disconnect and remove the account, if we have it...
		i = 0;
		while (i != theAccounts.size()) {
			if (theAccounts.get(i).getAccountSettings().getID() == as.getID()) {

				// only send out an event if we were online...
				if (theAccounts.get(i).isConnected()) {
					for (AccountConnectionListener acl : theACL) {
						acl.disconnectedWith(theAccounts.get(i));
					}
				}

				theAccounts.get(i).disconnect();
				theList.accountDisconnected(theAccounts.get(i));

				for (BuddyListChangeListener blcl : theBLCL) {
					blcl.BuddyListChange(theList);
				}

				theAccounts.remove(i);
				i = theAccounts.size();
			} else {
				i++;
			}
		}
	}

	public void setStatus(Status theStatus) {
		for (AbstractAccount aa : theAccounts) {
			aa.setStatus(theStatus);
		}
	}

	public void sendIM(IM theIM) {
		theIM.theAccount.sendIM(theIM);
	}

	public void addBuddyListChangeListener(BuddyListChangeListener blcl) {
		theBLCL.add(blcl);
	}

	public void addStatusChangeListener(StatusChangeListener scl) {
		theSCL.add(scl);
	}

	public void addAccountConnectionListener(AccountConnectionListener acl) {
		theACL.add(acl);
	}

	public BuddyList getBuddyList() {
		return theList;
	}

	public void addIMListener(IMListener iml) {
		theIml.add(iml);
	}

	public void buddyStatusChange(Buddy theBuddy, boolean firstTime) {
		// System.out.println(theBuddy.getScreename());
		if (firstTime) {
			theList.addBuddy(theBuddy);
		} else {
			theList.updateBuddy(theBuddy);
		}

		for (BuddyListChangeListener blcl : theBLCL) {
			// System.out.println("Notify");
			blcl.BuddyListChange(theList);
		}

		if (firstTime)
			return;

		for (StatusChangeListener scl : theSCL) {
			scl.changeStatus(theBuddy);
		}
	}

	public void gotIM(IM theIM) {
		for (IMListener theL : theIml) {
			theL.gotIM(theIM);
		}
	}

	public void loggedIn(AbstractAccount theAccount) {
		for (AccountConnectionListener acl : theACL) {
			acl.connectedWith(theAccount);
		}
	}

	public void buddyDeleted(Buddy theBuddy) {
		theList.removeBuddy(theBuddy);
	}

	public void buddyAliasChanged(Buddy b) {
		theList.updateBuddy(b);
		for (BuddyListChangeListener blcl : theBLCL) {
			blcl.BuddyListChange(theList);
		}
	}

	public void loginError(AbstractAccount theAccount) {
		// TODO Auto-generated method stub
		System.out.println("Caught login error on: "
				+ theAccount.getAccountSettings().getAlias());
	}

}
