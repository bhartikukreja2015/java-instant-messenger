package org.bubuntux.jim.runner;

import org.bubuntux.jim.abstractionLayer.AccountSettings;
import org.bubuntux.jim.preferences.EncryptionPoint;
import org.bubuntux.jim.preferences.PreferencePoint;

public class pbeTest {
	public static void main(String[] args) {
		EncryptionPoint myPoint = new EncryptionPoint("test password");

		PreferencePoint pp = new PreferencePoint(myPoint);

		pp.deleteAllAccounts();

		AccountSettings mySettings = new AccountSettings();
		mySettings.setAccountType(AccountSettings.AIMAccount);
		mySettings.setAlias("Test");
		mySettings.setEnabled(true);
		mySettings.setID(1);
		mySettings.setPassword("Encrypted!");
		mySettings.setUsername("Stored");

		System.out.println(mySettings.toString());

		pp.saveAccount(mySettings);

		mySettings = new AccountSettings();
		mySettings = pp.getAllAccounts().get(0);

		System.out.println(mySettings.toString());
		// b = myPoint.decrypt(b);
		// System.out.println(b.toString());
	}
}
