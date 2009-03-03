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
		String password = (myE != null ? new String(myE.encrypt(myAccount.getPassword().getBytes())) : myAccount.getPassword());
		accountNode.put(PreferencePoint.passwordKey, password);
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
				
				String currentPass = accountNode.node(s).get(PreferencePoint.passwordKey, "");
				currentPass = (myE != null ? new String(myE.decrypt(currentPass.getBytes())) : currentPass);
				
				myA.setPassword(currentPass);
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
			String currentPass = accountNode.get(PreferencePoint.passwordKey, "");
			currentPass = (myE != null ? new String(myE.decrypt(currentPass.getBytes())) : currentPass);
			
			myA.setPassword(currentPass);
			myA.setAccountType(accountNode.get(PreferencePoint.accountTypeKey, ""));
			myA.setEnabled(accountNode.getBoolean(PreferencePoint.enabledKey, false));
			myA.setAlias(accountNode.get(PreferencePoint.accountAlias, ""));

			return myA;
		} catch (BackingStoreException e) {
			
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
		Preferences aliasNode = myPrefs.node("Aliases");
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
	
}
