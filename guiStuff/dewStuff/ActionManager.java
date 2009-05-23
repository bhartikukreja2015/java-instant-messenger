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
	
	public ArrayList<Action> getActionList(String q) {
		ArrayList<Action> myActions = new ArrayList<Action>();
		
		for (Action a : theActions) {
			if (a.isMatch(q)) { myActions.add(a); }
		}
		
		Collections.sort(Collections.synchronizedList(theActions));
		
		return myActions;
	}
	
	protected void sortActions() {
		Collections.sort(Collections.synchronizedList(theActions));
	}
	
	// add in actions that connect or disconnect a certain account
	protected void addAccountActions() {
		PreferencePoint pp = new PreferencePoint();
		ArrayList<AccountSettings> myAccount = pp.getAllAccounts();
		for (AccountSettings as : myAccount) {
			theActions.add(new AccountAction(as, theAM));
		}
	}

	// add in actions that open IM windows to users
	protected void addBuddyActions() {
		ArrayList<Buddy> myBuddies = theAM.getBuddyList().getAllBuddies();
		
		// ensure the list is sorted...
		Collections.sort(Collections.synchronizedList(myBuddies));
		
		for (Buddy b : myBuddies) {
			theActions.add(new BuddyAction(b, theIMWM));
		}
	}
	
	public void BuddyListChange(BuddyList b) {
		// clear the list...
		int i = 0;
		while (i != theActions.size()) {
			if (theActions.get(i) instanceof BuddyAction) {
				theActions.remove(i);
			} else {
				i++;
			}
		}
		
		// and recreate it...
		addBuddyActions();
	}
	
}
