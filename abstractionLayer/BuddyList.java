package abstractionLayer;

import java.util.ArrayList;

public class BuddyList {
	protected ArrayList<Buddy> theList;
	protected boolean isSorting;
	
	
	public BuddyList(boolean shouldSort) {
		theList = new ArrayList<Buddy>();
		isSorting = shouldSort;
	}
	
	public void addBuddy(Buddy theBuddy) { 
		theList.add(theBuddy);
		if (isSorting) { sortList(); }
	}
	public void removeBuddy(Buddy theBuddy) { 
		theList.remove(theBuddy);
		if (isSorting) { sortList(); }
	}
	
	public void updateBuddy(Buddy theBuddy) {
		int i = 0;
		while (i != theList.size()) {
			if (theList.get(i).getScreename().equals(theBuddy.getScreename())) {
				
				theList.set(i, theBuddy);
				
				if (isSorting) { sortList(); }
				return;
			}
			i++;
		}
		
		// we didn't find the buddy, so add it.
		addBuddy(theBuddy);
		if (isSorting) { sortList(); }
	}
	
	public ArrayList<Buddy> getAllBuddies() { return theList; }
	
	public void printBuddyList() {
		for (Buddy b : theList) {
			System.out.println(b.getScreename() + "|" + b.getAlias() + "|" + b.getStatus());
		}
		System.out.println("Stop");
	}
	
	public String getAlias(String username) {
		for (Buddy b : theList) {
			if (b.getScreename().equals(username)) {
				return b.getAlias();
			}
		}
		
		return "";
	}
	
	protected void sortList() {
		// selection sort method
		int i = 0, count = 0;
		
		// get all av. users on top
		while (count != theList.size()) {
			if (theList.get(count).getStatus() == Buddy.available) {
				// swap this one out with whatever i we are on
				Buddy b = theList.get(i);
				theList.set(i, theList.get(count));
				theList.set(count, b);
				i++;
			}
			count++;
		}
		
		// now get everyone who is away
		count = i;
		while (count != theList.size()) {
			if (theList.get(count).getStatus() == Buddy.away) {
				Buddy b = theList.get(i);
				theList.set(i, theList.get(count));
				theList.set(count, b);
				i++;
			}
			count++;
		}
		
		// now everyone who isn't offline
		count = i;
		while (count != theList.size()) {
			if (theList.get(count).isOnline()) {
				Buddy b = theList.get(i);
				theList.set(i, theList.get(count));
				theList.set(count, b);
				i++;
			}
			count++;
		}
	}
	
}
