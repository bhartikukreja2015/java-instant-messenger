package guiStuff.dewStuff;

import java.awt.Image;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;

import upperAbstractionLayer.AccountManager;

import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;

public class AccountAction extends Action {

	protected AccountSettings theSettings;
	protected AccountManager AM;
	
	public AccountAction(AccountSettings as, AccountManager theAM) { 
		theSettings = as;
		AM = theAM;
	}
	
	public void execute() {
		if (isConnected()) {
			AM.disconnectAccount(theSettings);
		} else { 
			AM.connectWithTemplate(theSettings);
		}
	}

	public String getDesc() {
		if (isConnected()) {
			return "Disconnect the account " + theSettings.getAlias();
		} else {
			return "Connect with the account " + theSettings.getAlias();
		}
	}

	public Image getImage() {
		PreferencePoint pp = new PreferencePoint();
		IconFetch IF = new IconFetch(pp.getIconTheme());
		
		return IF.loadImage(theSettings.getAccountType());
	}

	public String getName() {
		if (isConnected()) {
			return "Disconnect " + theSettings.getAlias();
		} else {
			return "Connect with " + theSettings.getAlias();
		}
	}
	
	public boolean isConnected() {
		// see if we are connected...
		AbstractAccount as = AM.getAccount(theSettings);
		return as.isConnected();
	}

	public int compareTo(Object o) {
		if (o instanceof BuddyAction) {
			if (((BuddyAction) o).toShow.isOnline()) { 
				return 1;
			} else {
				return -1;
			}
		}
		
		if (o instanceof AccountAction) {
			if (((AccountAction) o).isConnected()) {
				return (this.isConnected() ? 0 : 1);
			} else {
				return (this.isConnected() ? -1 : 0);
			}
		}
		
		return 0;
	}
	
	public boolean isMatch(String q) {
		if (q.equals("")) return false;
		
		String s = theSettings.getAccountType() + theSettings.getAlias() + theSettings.getUsername() + this.getName() + this.getDesc();
		if (s.indexOf(q) != -1) { return true; }
		
		// check space sep
		// do we have a space?
		if (s.indexOf(" ") == -1) { return false; }
		
		// we do have a space!
		// check it..
		String sub = s.substring(0, s.indexOf(" "));
		if (s.indexOf(sub) != -1)  { return true; }
		
		// it was not found. Check the next term...
		return isMatch(s.substring(s.indexOf(" ") + 1));
	}
	
}
