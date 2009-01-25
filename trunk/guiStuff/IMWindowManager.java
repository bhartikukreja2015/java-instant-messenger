package guiStuff;

import java.util.ArrayList;

import abstractionLayer.Buddy;
import abstractionLayer.BuddyList;
import abstractionLayer.IM;
import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.BuddyListChangeListener;
import upperAbstractionLayer.IMListener;

public class IMWindowManager implements IMListener, BuddyListChangeListener {
	AccountManager myAM;
	ArrayList<IMWindow> theWindows;
	
	public IMWindowManager(AccountManager theAM) {
		myAM = theAM;
		myAM.addIMListener(this);
		myAM.addBuddyListChangeListener(this);
		
		theWindows = new ArrayList<IMWindow>();
	}

	public void gotIM(IM theIM) {
		for (IMWindow imw : theWindows) {
			if (imw.getTo().getScreename().equals(theIM.from)) {
				imw.showIM(theIM);
				return;
			}
		}
		
		
		// if we are still here, we need to create a new window.
		// we need to get the buddy.
		// see if they are on the buddy list.
		
		Buddy myB = null;
		ArrayList<Buddy> theList = myAM.getBuddyList().getAllBuddies();
		
		
		if (theList != null) {
			for (Buddy b : theList) {
				if (b.getScreename().equals(theIM.from)) {
					myB = b;
					break;
				}
			}
		}
			
		
		
		if (myB == null) {
			// we got an IM from a buddy not on the BL.
			myB = new Buddy();
			myB.setScreename(theIM.from);
		}
		
		// myB will now be the buddy we need to add.
		
		
		IMWindow imw = new IMWindow(this);
		imw.setTo(myB);
		imw.showIM(theIM);
		
		theWindows.add(imw);
		
		
	}

	public void BuddyListChange(BuddyList b) {
	
	}
	
	public void createIMWindow(Buddy b) {
		IMWindow imw = new IMWindow(this);
		imw.setTo(b);
		
		theWindows.add(imw);
	}

	public void sendIM(IM theIM) {
		for (IMWindow w : theWindows) {
			if (w.getTo().getScreename().equals(theIM.to)) {
				// give it to this window
				w.showIM(theIM);
				myAM.sendIM(theIM);
				return;
			}
		}
	}
	
	
}
