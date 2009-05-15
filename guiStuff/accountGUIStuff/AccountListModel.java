package guiStuff.accountGUIStuff;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import jimPreferences.PreferencePoint;

import abstractionLayer.AccountSettings;

public class AccountListModel implements ListModel {

	ArrayList<AccountSettings> theList;
	ArrayList<ListDataListener> theListeners;
	
	AccountListModel(PreferencePoint pp) {
		theList = pp.getAllAccounts();
		theListeners = new ArrayList<ListDataListener>();
	}
	
	public void addListDataListener(ListDataListener arg0) {
		theListeners.add(arg0);

	}

	public Object getElementAt(int arg0) {
		return theList.get(arg0);
	}

	public int getSize() {
		return theList.size();
	}
	
	public void update(PreferencePoint pp) {
		theList = pp.getAllAccounts();
		
		for (ListDataListener ldl : theListeners) {
			ldl.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
		}
	}

	public void removeListDataListener(ListDataListener arg0) {
		theListeners.remove(arg0);
	}

}
