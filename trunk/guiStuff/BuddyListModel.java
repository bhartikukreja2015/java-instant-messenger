package guiStuff;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import abstractionLayer.BuddyList;

import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.BuddyListChangeListener;


public class BuddyListModel implements BuddyListChangeListener, ListModel {

	BuddyList theBuddyList;
	ArrayList<ListDataListener> theListeners;
	
	BuddyListModel(AccountManager theAM) {
		theAM.addBuddyListChangeListener(this);
		theBuddyList = theAM.getBuddyList();
		theListeners = new ArrayList<ListDataListener>();
	}
	
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
		return theBuddyList.getAllBuddies().get(arg0).getScreename();
	}

	public int getSize() {
		return theBuddyList.getAllBuddies().size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		theListeners.remove(arg0);
	}

}
