package runner;

import notificationStuff.NotificationManager;
import guiStuff.buddyListStuff.BuddyListWindow;
import guiStuff.imWindowStuff.IMWindowManager;
import upperAbstractionLayer.AccountManager;

public class jimLocal {
	public static void main(String[] args) {
		jimLocal jl = new jimLocal();
		jl.doIt();
	}
	
	public void doIt() {
		AccountManager myAM = new AccountManager();
		
		@SuppressWarnings("unused")
		// we never need to read it because it will do its job just sitting there.
		NotificationManager myNonMan = new NotificationManager(myAM);
		
		IMWindowManager myIMWM = new IMWindowManager(myAM);
		
		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
	}
}
