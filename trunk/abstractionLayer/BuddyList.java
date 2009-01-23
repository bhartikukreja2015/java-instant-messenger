package abstractionLayer;

import java.util.ArrayList;

public class BuddyList {
	protected ArrayList<Buddy> theList;
	
	public void addBuddy(Buddy theBuddy) { theList.add(theBuddy); }
	public void removeBuddy(Buddy theBuddy) { theList.remove(theBuddy); }
	
	public void updateBuddy(Buddy theBuddy) {
		int i = 0;
		while (i != theList.size()) {
			if (theList.get(i).screenname == theBuddy.screenname) {
				theList.set(i, theBuddy);
				return;
			}
			i++;
		}
		
		// we didn't find the buddy, so add it.
		addBuddy(theBuddy);
	}
	
	public ArrayList<Buddy> getAllBuddies() { return theList; }
	
}
