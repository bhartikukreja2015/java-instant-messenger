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
	protected ArrayList<Buddy> actualShown;
	
	protected Object selectedObject;
	
	public BuddyListModel(AccountManager theAM) {
		theAM.addBuddyListChangeListener(this);
		theBuddyList = theAM.getBuddyList();
		toShow = new ArrayList<Buddy>();
		actualShown = new ArrayList<Buddy>();
		theListeners = new ArrayList<ListDataListener>();
		showOffline = true;
		hideMerged = true;
		
		updateToShow();
	}
	
	public BuddyListModel(BuddyList b) {
		theBuddyList = b;
		toShow = new ArrayList<Buddy>();
		actualShown = new ArrayList<Buddy>();
		theListeners = new ArrayList<ListDataListener>();
		showOffline = true;
		hideMerged = false;
		updateToShow();
	}
	
	protected void updateToShow() {
		// first, load the new arraylist of buddies
		toShow = theBuddyList.getAllBuddies();
		
		// show everyone by default... check to see if we are showing offline users
		for (Buddy b : toShow) { 
			b.setShow(true);
			if (!this.isShowingOffline() && !b.isOnline()) {
				b.setShow(false);
			}
		}
		
		// deal with merges...
		
		if (hideMerged) {this.doMerge(); }
		
		// update actual shown...
		this.updateShownList();
		
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
		return actualShown.get(arg0);
	}

	public int getSize() {
		return actualShown.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		theListeners.remove(arg0);
	}

	protected void doMerge() {
		//System.out.println("Doing merge...");
		
		for (Buddy b : toShow) {
			if (b.getMergeID() != 0) {
				ArrayList<Buddy> inMerge = theBuddyList.getBuddiesInMerge(b.getMergeID());
				Buddy toPreserve = inMerge.get(0);
				for (Buddy bb : toShow) {
					if (bb.getMergeID() == b.getMergeID() && !bb.getScreename().equals(toPreserve.getScreename())) {
						bb.setShow(false);
					}
				}
			}
		}
	}

	public Object getSelectedItem() { return selectedObject; }
	public void setSelectedItem(Object arg0) { selectedObject = arg0; }
	
	protected void updateShownList() {
		actualShown = new ArrayList<Buddy>();
		for (Buddy b : toShow) {
			if (b.isShowing()) { actualShown.add(b); }
		}
	}
}
