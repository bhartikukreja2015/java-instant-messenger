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
		if (isSorting) { theList = sortList(theList); }
	}
	public void removeBuddy(Buddy theBuddy) { 
		int i = 0;
		while (i != theList.size()) {
			if (theList.get(i).getScreename() == theBuddy.getScreename()) {
				if (theList.get(i).getAccount().getAccountSettings().getID() == theBuddy.theAccount.getAccountSettings().getID()) {
					theList.remove(i);
					break;
				}
			}
			i++;
		}
		if (isSorting) { theList = sortList(theList); }
	}
	
	public void updateBuddy(Buddy theBuddy) {
		int i = 0;
		while (i != theList.size()) {
			if (theList.get(i).getScreename().equals(theBuddy.getScreename())) {
				
				// some IM services don't support alias
				// so if we have an alias set, but we were not sent an alias
				// make sure to maintain that alias
				if (theBuddy.getAlias() == null) {
					// we can replace null with null
					theBuddy.setAlias(theList.get(i).getAlias());
				}
				
				theList.set(i, theBuddy);
				
				if (isSorting) { theList = sortList(theList); }
				return;
			}
			i++;
		}
		
		// we didn't find the buddy, so add it.
		addBuddy(theBuddy);
		if (isSorting) { theList = sortList(theList); }
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
	
	protected ArrayList<Buddy> sortList(ArrayList<Buddy> ourList) {
		// selection sort method
		int i = 0, count = 0;
		
		// get all av. users on top
		while (count != ourList.size()) {
			if (ourList.get(count).getStatus() == Buddy.available) {
				// swap this one out with whatever i we are on
				Buddy b = ourList.get(i);
				ourList.set(i, ourList.get(count));
				ourList.set(count, b);
				i++;
			}
			count++;
		}
		
		// now get everyone who is away
		count = i;
		while (count != ourList.size()) {
			if (ourList.get(count).getStatus() == Buddy.away) {
				Buddy b = ourList.get(i);
				ourList.set(i, ourList.get(count));
				ourList.set(count, b);
				i++;
			}
			count++;
		}
		
		// now everyone who isn't offline
		count = i;
		while (count != ourList.size()) {
			if (ourList.get(count).isOnline()) {
				Buddy b = ourList.get(i);
				ourList.set(i, ourList.get(count));
				ourList.set(count, b);
				i++;
			}
			count++;
		}
		
		return ourList;
	}
	
	
	public int getOfflineCount() {
		int i = 0;
		for (Buddy b : theList) {
			if (!b.isOnline()) { i++; }
		}
		
		return i;
	}
	
	public Buddy getTopOfMerge(int mergeID) {
		Buddy myB = new Buddy();
		
		ArrayList<Buddy> theMerge = new ArrayList<Buddy>();
		
		for (Buddy b : theList) {
			if (myB.mergeID == mergeID) {
				theMerge.add(b);
			}
		}
		
		theMerge = sortList(theMerge);
		
		return myB;
	}
	
}