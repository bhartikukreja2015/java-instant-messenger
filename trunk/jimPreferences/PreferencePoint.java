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
	protected static final String enabledKey = "e";
	protected static final String accountAlias = "a";
	
	public static final String jimNotification = "j";
	public static final String notifysendNotification = "ns";
	
	
	protected EncryptionPoint myE;

	public PreferencePoint() {
		myPrefs = Preferences.userRoot();

		// select the node
		// the method will create the node if it does not already exist
		myPrefs = myPrefs.node("Jim IM Client");
	}
	
	public PreferencePoint(EncryptionPoint ep) {
		myPrefs = Preferences.userRoot();
		myPrefs = myPrefs.node("Jim IM Client");
		myE = ep;
	}

	public void saveAccount(AccountSettings myAccount) {
		Preferences accountNode = myPrefs.node("Accounts").node("" + myAccount.getID());

		accountNode.put(PreferencePoint.usernameKey, myAccount.getUsername());
		if (myE != null) {
			accountNode.putByteArray(PreferencePoint.passwordKey, myE.encrypt(myAccount.getPassword().getBytes()));
		} else {
			accountNode.put(PreferencePoint.passwordKey, myAccount.getPassword());
		}
		accountNode.put(PreferencePoint.accountTypeKey, myAccount.getAccountType());
		accountNode.putBoolean(PreferencePoint.enabledKey, myAccount.isEnabled());		
		accountNode.put(PreferencePoint.accountAlias, myAccount.getAlias());
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
				if (myE != null) {
					myA.setPassword(new String(myE.decrypt(accountNode.node(s).getByteArray(PreferencePoint.passwordKey, null))));
				} else {
					myA.setPassword(accountNode.node(s).get(PreferencePoint.passwordKey, ""));
				}
				myA.setAccountType(accountNode.node(s).get(PreferencePoint.accountTypeKey, ""));
				myA.setEnabled(accountNode.node(s).getBoolean(PreferencePoint.enabledKey, false));
				myA.setAlias(accountNode.node(s).get(PreferencePoint.accountAlias, ""));

				toReturn.add(myA);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		return toReturn;
	}
	
	public AccountSettings getAccount(int id) {
		Preferences accountNode = myPrefs.node("Accounts");
		try {
			if (!myPrefs.nodeExists("" + id)) return null;
			myPrefs = myPrefs.node("" + id);
			AccountSettings myA = new AccountSettings();

			myA.setID(id);
			myA.setUsername(accountNode.get(PreferencePoint.usernameKey, ""));
			if (myE != null) {
				myA.setPassword(new String(myE.decrypt(accountNode.getByteArray(PreferencePoint.passwordKey, null))));
			} else {
				myA.setPassword(accountNode.get(PreferencePoint.passwordKey, ""));
			}
			myA.setAccountType(accountNode.get(PreferencePoint.accountTypeKey, ""));
			myA.setEnabled(accountNode.getBoolean(PreferencePoint.enabledKey, false));
			myA.setAlias(accountNode.get(PreferencePoint.accountAlias, ""));

			return myA;
		} catch (BackingStoreException e) {
			
		}
		
		return null;
		
	}
	
	public AccountSettings getAccount(String alias) {
		ArrayList<AccountSettings> mySettings = this.getAllAccounts();
		for (AccountSettings as : mySettings) {
			if (as.getAlias().equals(alias)) { return as; }
		}
		return null;
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
	
	public String getAliasForScreenname(String screenname, String accountName) {
		Preferences aliasNode = myPrefs.node("Aliases");;
		aliasNode = aliasNode.node(accountName);
		return aliasNode.get(screenname, null);
	}
	
	public void setAliasForScreenname(String screenname, String accountName, String alias) {
		Preferences aliasNode = myPrefs.node("Aliases");
		
		aliasNode = aliasNode.node(accountName);
		
		aliasNode.put(screenname, alias);
	}
	
	public int getMergeIDForScreenname(String screenname, String accountName) {
		Preferences aliasNode = myPrefs.node("Merge");
		aliasNode = aliasNode.node(accountName);
		
		return aliasNode.getInt(screenname, 0);
	}
	
	public void setMergeIDForScreenname(String screenname, String accountName, int mergeID) {
		
		//System.out.println("Setting merge ID of " + screenname + " to " + mergeID);
		
		Preferences aliasNode = myPrefs.node("Merge");
		
		aliasNode = aliasNode.node(accountName);
		
		aliasNode.putInt(screenname, mergeID);
	}
	
	public String getIconTheme() {
		Preferences random = myPrefs.node("Pref");
		return random.get("icontheme", "jim");
	}
	
	public void setIconTheme(String theme) {
		Preferences random = myPrefs.node("Pref");
		random.put("icontheme", theme);
	}
	
	public String getNotificationType() {
		Preferences random = myPrefs.node("Pref");
		return random.get("nottype", "jim");
	}
	
	public void setNotificationType(String notType) {
		Preferences random = myPrefs.node("Pref");
		random.put("nottype", notType);
	}
	
}
