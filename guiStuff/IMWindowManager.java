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
	BuddyList theBL;
	
	public IMWindowManager(AccountManager theAM) {
		myAM = theAM;
		myAM.addIMListener(this);
		myAM.addBuddyListChangeListener(this);
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
		for (Buddy b : theBL.getAllBuddies()) {
			if (b.getScreename().equals(theIM.from)) {
				myB = b;
				break;
			}
		}
		
		if (myB == null) {
			// we got an IM from a buddy not on the BL.
			myB = new Buddy();
			myB.setScreename(theIM.from);
		}
		
		// myB will now be the buddy we need to add.
		
		IMWindow imw = new IMWindow();
		imw.setTo(myB);
		imw.showIM(theIM);
		
		
	}

	public void BuddyListChange(BuddyList b) {
		theBL = b;
	}
}
