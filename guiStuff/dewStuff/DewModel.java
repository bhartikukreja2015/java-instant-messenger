package guiStuff.dewStuff;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class DewModel implements ListModel {

	protected ActionManager theAM;
	protected ArrayList<ListDataListener> myList;
	protected String query;
	protected ArrayList<Action> theActions;
	
	public DewModel(ActionManager AM) {
		theAM = AM;
		myList = new ArrayList<ListDataListener>();
		theActions = new ArrayList<Action>();
	}
	
	public void setQuery(String q) {
		query = q;
		
		theActions = theAM.getActionList(q);
		
		for (ListDataListener LDL : myList) {
			LDL.contentsChanged(null);
		}
	}
	
	public void addListDataListener(ListDataListener arg0) {
		myList.add(arg0);
	}

	public Object getElementAt(int arg0) {
		return theActions.get(arg0);
	}

	public int getSize() {
		return theActions.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		myList.remove(arg0);
	}

}
