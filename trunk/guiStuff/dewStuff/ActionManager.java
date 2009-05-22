package guiStuff.dewStuff;

import guiStuff.imWindowStuff.IMWindowManager;

import java.util.ArrayList;
import java.util.Collections;

import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.BuddyListChangeListener;

import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;

import jimPreferences.PreferencePoint;

public class ActionManager implements BuddyListChangeListener {
	protected ArrayList<Action> theActions;
	protected AccountManager theAM;
	protected IMWindowManager theIMWM;
	
	public ActionManager(AccountManager AM, IMWindowManager IMW) { 
		theActions = new ArrayList<Action>();
		theAM = AM;
		theIMWM = IMW;
	}
	
	public void clearActions() { theActions = new ArrayList<Action>(); }
	
	// add in actions that connect or disconnect a certain account
	protected void addAccountActions() {
		PreferencePoint pp = new PreferencePoint();
		ArrayList<AccountSettings> myAccount = pp.getAllAccounts();
		for (AccountSettings as : myAccount) {
			theActions.add(new AccountAction(as, theAM));
		}
	}


	
	// add in actions that open IM windows to online users
	protected void addBuddyActions() {
		ArrayList<Buddy> myBuddies = theAM.getBuddyList().getAllBuddies();
		
		// ensure the list is sorted...
		Collections.sort(Collections.synchronizedList(myBuddies));
		
		for (Buddy b : myBuddies) {
			theActions.add(new BuddyAction(b, theIMWM));
		}
	}
	
	public void BuddyListChange(BuddyList b) {
		// TODO Auto-generated method stub
		
	}
	
}
