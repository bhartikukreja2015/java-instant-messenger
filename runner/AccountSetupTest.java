package runner;

import jimPreferences.PreferencePoint;
import guiStuff.AccountSettingsWindow;

public class AccountSetupTest {

	public static void main(String[] args) {
		AccountSetupTest ast = new AccountSetupTest();
		ast.doIt();
	}
	
	public void doIt() {
		AccountSettingsWindow myWin = new AccountSettingsWindow();
		myWin.setVisible(true);
	}

}
