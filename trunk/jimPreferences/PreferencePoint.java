package jimPreferences;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import abstractionLayer.AccountSettings;

public class PreferencePoint {
	Preferences myPrefs;

	protected static final String usernameKey = "u";

	protected static final String passwordKey = "p";

	protected static final String accountTypeKey = "t";

	public PreferencePoint() {
		myPrefs = Preferences.userRoot();

		// select the node
		// the method will create the node if it does not already exist
		myPrefs = myPrefs.node("Jim IM Client");
	}

	public void saveAccount(AccountSettings myAccount) {
		Preferences accountNode = myPrefs.node("Accounts").node("" + myAccount.getID());

		accountNode.put(PreferencePoint.usernameKey, myAccount.getUsername());
		accountNode.put(PreferencePoint.passwordKey, myAccount.getPassword());
		accountNode.put(PreferencePoint.accountTypeKey, myAccount.getAccountType());
	}

	public ArrayList<AccountSettings> getAllAccounts() {
		ArrayList<AccountSettings> toReturn = new ArrayList<AccountSettings>();
		Preferences accountNode = myPrefs.node("Accounts");

		try {
			for (String s : accountNode.childrenNames()) {
				// everything in the account node is an account ID, with an
				// account inside.
				// start making the list
				AccountSettings myA = new AccountSettings();

				myA.setID(Integer.valueOf(s));
				myA.setUsername(accountNode.node(s).get(PreferencePoint.usernameKey, ""));
				myA.setPassword(accountNode.node(s).get(PreferencePoint.passwordKey, ""));
				myA.setAccountType(accountNode.node(s).get(PreferencePoint.accountTypeKey, ""));

				toReturn.add(myA);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	public int getNextAccountID() {
		Preferences accountNode = myPrefs.node("Accounts");
		int i = -1;

		try {
			for (String s : accountNode.childrenNames()) {
				i = Integer.valueOf(s);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		i++;
		return i;
	}
	
	public void deleteAccount(int id) {
		try {
			myPrefs.node("Accounts").node("" + id).removeNode();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllAccounts() {
		Preferences accountNode = myPrefs.node("Accounts");

		try {
			for (String s : accountNode.childrenNames()) {
				accountNode.node(s).removeNode();
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

	}
}
