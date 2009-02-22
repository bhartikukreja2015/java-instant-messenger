package runner;

import guiStuff.BuddyListWindow;
import guiStuff.IMWindowManager;
import upperAbstractionLayer.AccountManager;

public class jimLocal {
	public static void main(String[] args) {
		jimLocal jl = new jimLocal();
		jl.doIt();
	}
	
	public void doIt() {
		AccountManager myAM = new AccountManager();
		IMWindowManager myIMWM = new IMWindowManager(myAM);
		BuddyListWindow myBLW = new BuddyListWindow(myAM, myIMWM);
		myBLW.setVisible(true);
	}
}
