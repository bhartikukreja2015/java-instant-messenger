package guiStuff.statusStuff;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import abstractionLayer.Status;

public class StatusListModel implements ComboBoxModel {

	protected Object selected;
	protected ArrayList<ListDataListener> theListeners;
	protected ArrayList<String> theOptions;
	
	public StatusListModel() {
		theListeners = new ArrayList<ListDataListener>();
		theOptions = new ArrayList<String>();
		
		theOptions.add(Status.superAvailable);
		theOptions.add(Status.available);
		theOptions.add(Status.doNotDistrub);
		theOptions.add(Status.away);
		theOptions.add(Status.superAway);
		theOptions.add(Status.offline);
	}
	
	public Object getSelectedItem() {
		return selected;
	}

	public void setSelectedItem(Object anItem) {
		selected = anItem;
	}

	public void addListDataListener(ListDataListener l) {
		theListeners.add(l);
	}

	public Object getElementAt(int index) {
		return theOptions.get(index);
	}
	
	public int getSize() {
		return theOptions.size();
	}

	public void removeListDataListener(ListDataListener l) {
		theListeners.remove(l);
	}

}
