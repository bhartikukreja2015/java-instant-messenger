package guiStuff;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;

import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.BuddyListChangeListener;


public class BuddyListModel implements BuddyListChangeListener, ListModel, ComboBoxModel {

	protected BuddyList theBuddyList;
	protected ArrayList<ListDataListener> theListeners;
	protected boolean showOffline;
	protected boolean hideMerged;
	protected ArrayList<Buddy> toShow;
	
	protected Object selectedObject;
	
	public BuddyListModel(AccountManager theAM) {
		theAM.addBuddyListChangeListener(this);
		theBuddyList = theAM.getBuddyList();
		toShow = new ArrayList<Buddy>();
		theListeners = new ArrayList<ListDataListener>();
		showOffline = true;
		hideMerged = true;
		
		updateToShow();
	}
	
	public BuddyListModel(BuddyList b) {
		theBuddyList = b;
		toShow = new ArrayList<Buddy>();
		theListeners = new ArrayList<ListDataListener>();
		showOffline = true;
		hideMerged = false;
		updateToShow();
	}
	
	protected void updateToShow() {
		// first, load the new arraylist of buddies
		toShow = theBuddyList.getAllBuddies();
		
		// see if we are showing offline users
		if (!showOffline) {
			// we are not showing offline users... remove any online users from the list.
			int i = 0;
			while (i != toShow.size()) {
				if (!toShow.get(i).isOnline()) {
					// this user isn't offline... they don't get to be shown!
					toShow.remove(i);
					i = 0;
				} else {
					i++;
				}
			}
		}
		
		// deal with merges...
		
		if (hideMerged) {this.doMerge(); }
		
		// let everybody know..
		for (ListDataListener ldl : theListeners) {
			ldl.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
		}
	}
	
	public void setShowOffline(boolean b) {
		showOffline = b;
		updateToShow();
	}
	
	public boolean isShowingOffline() { return showOffline; }
	
	public void setHideMerged(boolean b) {
		hideMerged = b;
		updateToShow();
	}
	
	public boolean isHidingMerged() { return hideMerged; }
	
	public void BuddyListChange(BuddyList b) {
		theBuddyList = b;
		updateToShow();
	}

	public void addListDataListener(ListDataListener arg0) {
		theListeners.add(arg0);
	}

	public Object getElementAt(int arg0) {
		return toShow.get(arg0);
	}

	public int getSize() {
		return toShow.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		theListeners.remove(arg0);
	}

	protected void doMerge() {
		int count = 0;
		while (count != toShow.size()) {
			Buddy b = toShow.get(count);
			
			int mergeID = b.getMergeID();
			if (mergeID == 0) { count++; break; }
			// if we are still here we have a merge ID
			ArrayList<Buddy> usersInMerge = theBuddyList.getBuddiesInMerge(mergeID);
			
			// the topmost user (index: 0) of usersInMerge is the user we are going to show
			// all the rest need to be removed from the list
			
			// 1. Make sure the topmost user will be shown
			toShow.set(count, usersInMerge.get(0));
			usersInMerge.remove(0); // we don't want to remove that user from the list
			
			// 2. Remove all the rest of the users from the list
			for (Buddy theBud : usersInMerge) {
				int i = 0;
				while (i != toShow.size()) {
					if (theBud.getScreename().equals(toShow.get(i).getScreename())) {
						// we need to remove this user
						toShow.remove(i);
						i = toShow.size(); // make sure the loop doesn't contintue
					} else {
						i++;
					}
				}
			}
			
			count++;
		}
	}

	public Object getSelectedItem() { return selectedObject; }
	public void setSelectedItem(Object arg0) { selectedObject = arg0; }
}
