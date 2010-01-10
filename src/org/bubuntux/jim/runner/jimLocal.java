package org.bubuntux.jim.runner;

import org.bubuntux.jim.gui.buddyList.BuddyListWindow;
import org.bubuntux.jim.gui.imWindow.IMWindowManager;
import org.bubuntux.jim.gui.notification.NotificationManager;
import org.bubuntux.jim.upperAbstractionLayer.AccountManager;

public class jimLocal {
	public static void main(String[] args) {
		jimLocal jl = new jimLocal();
		jl.doIt();
	}

	public void doIt() {
		AccountManager myAM = new AccountManager();

		@SuppressWarnings("unused")
		// we never need to read it because it will do its job just sitting
		// there.
		NotificationManager myNonMan = new NotificationManager(myAM);

		IMWindowManager myIMWM = new IMWindowManager(myAM);

		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
	}
}
