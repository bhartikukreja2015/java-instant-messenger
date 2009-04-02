package notificationStuff;

import abstractionLayer.AbstractAccount;
import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.Status;
import upperAbstractionLayer.AccountConnectionListener;
import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.IMListener;
import upperAbstractionLayer.StatusChangeListener;

public class NotificationManager implements StatusChangeListener, IMListener, AccountConnectionListener {
	
	AccountManager AM;
	
	public NotificationManager(AccountManager theAM) {
		AM = theAM;
		theAM.addIMListener(this);
		theAM.addStatusChangeListener(this);
		theAM.addAccountConnectionListener(this);
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
		
		
		theNot.setIconHint(b.getStatus().getStatus());
		
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

	public void connectedWith(AbstractAccount aa) {
		Notification theNot = new Notification(Notification.ConnectingAccount);
		
		theNot.setIconHint(aa.getAccountSettings().getAccountType());
		theNot.setSubject("Connceted " + aa.getAccountSettings().getAlias());
		theNot.setMessage("S/N: " + aa.getAccountSettings().getUsername());
		
		theNot.dispatch();
	}

	public void disconnectedWith(AbstractAccount aa) {
		Notification theNot = new Notification(Notification.AccountDisconnected);
		
		theNot.setIconHint(aa.getAccountSettings().getAccountType());
		theNot.setSubject(aa.getAccountSettings().getAlias() + " disconnected");
		theNot.setMessage("S/N: " + aa.getAccountSettings().getUsername());
		
		theNot.dispatch();
	}

	public void startingConnection(AbstractAccount aa) {
	Notification theNot = new Notification(Notification.ConnectingAccount);
		
		theNot.setIconHint(aa.getAccountSettings().getAccountType());
		theNot.setSubject("Connecting with " + aa.getAccountSettings().getAlias() + "...");
		theNot.setMessage("S/N: " + aa.getAccountSettings().getUsername());
		
		theNot.dispatch();
	}
}
