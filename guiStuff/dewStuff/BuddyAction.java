package guiStuff.dewStuff;

import guiStuff.imWindowStuff.IMWindowManager;

import java.awt.Image;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;

import abstractionLayer.Buddy;
import abstractionLayer.Status;

public class BuddyAction extends Action {


	protected IMWindowManager theIMWM;
	public Buddy toShow;
	
	public BuddyAction(Buddy b, IMWindowManager theM) {
		theIMWM = theM;
		toShow = b;
	}
	
	public void execute() {
		theIMWM.createIMWindow(toShow);
	}

	
	public String getDesc() {
		return "Send an IM to " + toShow.getAlias();
	}

	public Image getImage() {
		PreferencePoint pp = new PreferencePoint();
		IconFetch myFetch = new IconFetch(pp.getIconTheme());
		
		return myFetch.loadImage(Status.superAvailable);
	}

	public String getName() {
		return "IM " + toShow.getAlias();
	}
	
	public int compareTo(Object o) {
		if (o instanceof AccountAction) {
			if (toShow.isOnline()) { 
				return -1;
			} else {
				return 1;
			}
		}
		
		if (o instanceof BuddyAction) {
			if (((BuddyAction) o).toShow.isOnline()) {
				return (toShow.isOnline() ? 0 : 1);
			} else {
				return (toShow.isOnline() ? -1 : 0);
			}
		}
		
		return 0;
	}

}
