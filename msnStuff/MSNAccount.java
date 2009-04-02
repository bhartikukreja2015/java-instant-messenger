package msnStuff;

import net.sf.jml.Email;
import net.sf.jml.MsnContact;
import net.sf.jml.MsnContactList;
import net.sf.jml.MsnGroup;
import net.sf.jml.MsnMessenger;
import net.sf.jml.MsnSwitchboard;
import net.sf.jml.MsnUserStatus;
import net.sf.jml.event.MsnContactListListener;
import net.sf.jml.event.MsnMessageListener;
import net.sf.jml.event.MsnMessengerListener;
import net.sf.jml.impl.MsnMessengerFactory;
import net.sf.jml.message.MsnControlMessage;
import net.sf.jml.message.MsnDatacastMessage;
import net.sf.jml.message.MsnInstantMessage;
import net.sf.jml.message.MsnSystemMessage;
import net.sf.jml.message.MsnUnknownMessage;
import net.sf.jml.message.p2p.MsnP2PMessage;
import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;
import abstractionLayer.Status;

public class MSNAccount implements AbstractAccount, MsnMessageListener, MsnMessengerListener, MsnContactListListener {

	protected AccountSettings theAS;
	protected IMEvents theEvents;
	
	protected MsnMessenger myCon;
	
	protected boolean isConn;
	
	protected boolean haveDispatched;
	
	public MSNAccount() {
		haveDispatched = false;
		isConn = false;
	}
	
	public void addBuddy(Buddy theBuddy) {
		myCon.addFriend(Email.parseStr(theBuddy.getScreename()), theBuddy.getAlias());
	}

	public void connect() {
		myCon = MsnMessengerFactory.createMsnMessenger(theAS.getUsername(), theAS.getPassword());
		
		// turn off logging
		myCon.setLogIncoming(false);
		myCon.setLogOutgoing(false);
		
		// add ourselves to the listener lists we care about
		myCon.addMessageListener(this);
		myCon.addMessengerListener(this);
		myCon.addContactListListener(this);
		
		// log in
		myCon.login();
		
	}

	public void disconnect() { myCon.logout(); isConn = false; }
	public AccountSettings getAccountSettings() { return theAS; }

	public boolean isConnected() { return isConn; }

	public void sendIM(IM theIM) {
		myCon.sendText(Email.parseStr(theIM.to), theIM.message);
	}

	public void setAccountSettings(AccountSettings as) {
		theAS = as;
		
	}

	public void setListener(IMEvents theEvent) { theEvents = theEvent; }

	public void setStatus(Status theStatus) {
		MsnUserStatus toChange = null;
		
		if (theStatus.getStatus().equals(Status.available) || theStatus.getStatus().equals(Status.superAvailable)) {
			toChange = MsnUserStatus.ONLINE;
		} else if (theStatus.getStatus().equals(Status.away)) {
			toChange = MsnUserStatus.BE_RIGHT_BACK;
		} else if (theStatus.getStatus().equals(Status.doNotDistrub)) {
			toChange = MsnUserStatus.BUSY;
		} else if (theStatus.getStatus().equals(Status.superAway)) {
			toChange = MsnUserStatus.OUT_TO_LUNCH;
		}
		
		myCon.getOwner().setStatus(toChange);
	}
	
	protected Buddy MSNContactToJimBuddy(MsnContact myMC) {
		Buddy b = new Buddy();
		
		b.setAccount(this);
		b.setScreename(myMC.getEmail().toString());
		b.setAlias(myMC.getFriendlyName());
		
		MsnUserStatus myStatus = myMC.getStatus();
		
		// we want to use status
		
		//System.out.println(myStatus.getDisplayStatus() + "|" + MsnUserStatus.OFFLINE);
		
		Status toSet = new Status();
		
		if (myStatus == MsnUserStatus.AWAY || myStatus == MsnUserStatus.BE_RIGHT_BACK || myStatus == MsnUserStatus.IDLE) {
			toSet.setStatus(Status.away);
		} else if (myStatus == MsnUserStatus.BUSY) {
			toSet.setStatus(Status.doNotDistrub);
		} else if (myStatus == MsnUserStatus.OUT_TO_LUNCH) {
			toSet.setStatus(Status.superAway);
		} else if (myStatus == MsnUserStatus.ONLINE) {
			toSet.setStatus(Status.available);
		} else if (myStatus == MsnUserStatus.OFFLINE) {
			toSet.setStatus(Status.offline);
		}
		
		b.setStatus(toSet);
		
		b.setGroupName(myMC.getBelongGroups()[0].getGroupName());
		
		
		return b;
	}
	
	protected void dispatchContactList(MsnContactList theList, boolean firstTime) {
		MsnContact[] theContacts = theList.getContacts();
		
		for (MsnContact myMC : theContacts) {
			//System.out.println(myMC);
			theEvents.buddyStatusChange(this.MSNContactToJimBuddy(myMC), firstTime);
		}
	}

	public void controlMessageReceived(MsnSwitchboard arg0, MsnControlMessage arg1, MsnContact arg2) { } // these are things like "typing"
	public void datacastMessageReceived(MsnSwitchboard arg0, MsnDatacastMessage arg1, MsnContact arg2) { } // docs are not specific -- some kind of streaming?
	public void systemMessageReceived(MsnMessenger arg0, MsnSystemMessage arg1) { } // dealt with internally
	public void unknownMessageReceived(MsnSwitchboard arg0, MsnUnknownMessage arg1, MsnContact arg2) { } // at this point in Jim, we'll just cry.
	public void exceptionCaught(MsnMessenger arg0, Throwable arg1) { } // do something... eventually
	public void contactAddedMe(MsnMessenger arg0, MsnContact arg1) { } // we should eventually notify the user
	public void contactRemovedMe(MsnMessenger arg0, MsnContact arg1) { } // we should tell the user eventually...
	public void groupAddCompleted(MsnMessenger arg0, MsnGroup arg1) { } // we aren't this far yet...
	public void groupRemoveCompleted(MsnMessenger arg0, MsnGroup arg1) { } // we are not this far yet
	public void p2pMessageReceived(MsnSwitchboard arg0, MsnP2PMessage arg1, MsnContact arg2) { } // looks kinda scary. Play with later.
	public void ownerStatusChanged(MsnMessenger arg0) { } // anybody?
	
	public void instantMessageReceived(MsnSwitchboard arg0, MsnInstantMessage arg1, MsnContact arg2) {
		IM myIM = new IM();
		myIM.theAccount = this;
		myIM.from = arg2.getEmail().toString();
		myIM.message = arg1.getContent();
		
		theEvents.gotIM(myIM);
	}

	public void loginCompleted(MsnMessenger arg0) { isConn = true; }

	public void logout(MsnMessenger arg0) { 
		// I believe that this gets called for a wrong password etc.
		// But here is a literal quote from the javadocs:
		// "Logout. Even not login completed may logout."
		theEvents.loginError(this);
		isConn = false;
	}

	public void contactAddCompleted(MsnMessenger arg0, MsnContact arg1) {
		Buddy b = this.MSNContactToJimBuddy(arg1);
		theEvents.buddyStatusChange(b, false);
	}

	public void contactListInitCompleted(MsnMessenger arg0) {
		// this is the inital contact list
		//System.out.println("Init");
		//if (haveDispatched) return;
		//System.out.println("Init");
		//haveDispatched = true;
		this.dispatchContactList(arg0.getContactList(), false);
	}

	public void contactListSyncCompleted(MsnMessenger arg0) {
		// we don't need both contactListInitCompleted and this firing.
		//System.out.println("Sync");
		//if (haveDispatched) return;
		//System.out.println("Sync");
		//haveDispatched = true;
		this.dispatchContactList(arg0.getContactList(), false);
		
	}

	public void contactRemoveCompleted(MsnMessenger arg0, MsnContact arg1) {
		theEvents.buddyDeleted(this.MSNContactToJimBuddy(arg1));
		
	}

	public void contactStatusChanged(MsnMessenger arg0, MsnContact arg1) {
		theEvents.buddyStatusChange(this.MSNContactToJimBuddy(arg1), false);
	}

	

}
