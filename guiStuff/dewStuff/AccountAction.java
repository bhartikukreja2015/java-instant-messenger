package guiStuff.dewStuff;

import java.awt.Image;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;

import upperAbstractionLayer.AccountManager;

import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;

public class AccountAction implements Action {

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
	
	protected boolean isConnected() {
		// see if we are connected...
		AbstractAccount as = AM.getAccount(theSettings);
		return as.isConnected();
	}

}
