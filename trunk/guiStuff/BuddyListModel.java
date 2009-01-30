package guiStuff;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;

import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.BuddyListChangeListener;


public class BuddyListModel implements BuddyListChangeListener, ListModel {

	protected BuddyList theBuddyList;
	protected ArrayList<ListDataListener> theListeners;
	protected boolean showOffline;
	
	public BuddyListModel(AccountManager theAM) {
		theAM.addBuddyListChangeListener(this);
		theBuddyList = theAM.getBuddyList();
		theListeners = new ArrayList<ListDataListener>();
		showOffline = true;
	}
	
	public void setShowOffline(boolean b) {
		showOffline = b;
		for (ListDataListener ldl : theListeners) {
			ldl.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
		}
	}
	
	public boolean isShowingOffline() { return showOffline; }
	
	public void BuddyListChange(BuddyList b) {
		theBuddyList = b;
		for (ListDataListener ldl : theListeners) {
			ldl.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
		}
	}

	public void addListDataListener(ListDataListener arg0) {
		theListeners.add(arg0);
	}

	public Object getElementAt(int arg0) {
		if (showOffline) {
			return theBuddyList.getAllBuddies().get(arg0);
		}
		
		// get a new list of all non-offline users
		ArrayList<Buddy> onlineUsers = new ArrayList<Buddy>();
		for (Buddy b : theBuddyList.getAllBuddies()) {
			if (b.isOnline()) { onlineUsers.add(b); }
		}
		
		return onlineUsers.get(arg0);
	}

	public int getSize() {
		if (theBuddyList.getAllBuddies() == null) { 
			return 0;
		}
		
		if (showOffline) {
			return theBuddyList.getAllBuddies().size();
		}
		
		return (theBuddyList.getAllBuddies().size() - theBuddyList.getOfflineCount()); 
	}

	public void removeListDataListener(ListDataListener arg0) {
		theListeners.remove(arg0);
	}

	protected void doMerge() {
		int count = 0;
		ArrayList<Buddy> theList = theBuddyList.getAllBuddies();
		
		for (Buddy b : theList) {
			int mergeID = b.getMergeID();
			if (mergeID == 0) { break; }
			// if we are still here
			// we have a merge id
			count = 0;
			
			while (count != theList.size()) {
				if (theList.get(count).getMergeID() == mergeID) {
					theList.remove(count);
					count = 0;
				}
				count++;
			}
			
			
		}
	}
}
