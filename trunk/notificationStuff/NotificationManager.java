package notificationStuff;

import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.Status;
import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.IMListener;
import upperAbstractionLayer.StatusChangeListener;

public class NotificationManager implements StatusChangeListener, IMListener {
	
	AccountManager AM;
	
	public NotificationManager(AccountManager theAM) {
		AM = theAM;
		theAM.addIMListener(this);
		theAM.addStatusChangeListener(this);
	}

	public void changeStatus(Buddy b) {
		Notification theNot = new Notification();
		
		if (b.getStatus().getStatus().equals(Status.offline)) {
			theNot.setType(Notification.BuddyOffline);
		} else if (!b.getStatus().getStatus().equals(Status.away) && !b.getStatus().getStatus().equals(Status.doNotDistrub) && !b.getStatus().getStatus().equals(Status.superAway)) {
			theNot.setType(Notification.BuddyOnline);
		} else {
			theNot.setType(Notification.BuddyStatusChange);
		}
		
		String ref = (b.getAlias() != null ? b.getAlias() : b.getScreename());
		
		theNot.setSubject(ref);
		theNot.setMessage(b.getStatus().getStatus());
		
		theNot.dispatch();
	}

	public void gotIM(IM theIM) {
		Buddy b = AM.getBuddyList().getBuddy(theIM.from);
		
		
		Notification theNot = new Notification(Notification.GotIM);
		
		String ref = (b.getAlias() != null ? b.getAlias() : b.getScreename());
		
		theNot.setSubject(ref);
		theNot.setMessage(theIM.message);
		
		theNot.dispatch();
	}
}
