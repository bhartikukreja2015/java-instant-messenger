package yahooStuff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import ymsg.network.AccountLockedException;
import ymsg.network.HackListen;
import ymsg.network.LoginRefusedException;
import ymsg.network.Session;
import ymsg.network.StatusConstants;
import ymsg.network.YMSG9Packet;
import ymsg.network.YahooGroup;
import ymsg.network.YahooUser;
import ymsg.network.event.SessionChatEvent;
import ymsg.network.event.SessionConferenceEvent;
import ymsg.network.event.SessionErrorEvent;
import ymsg.network.event.SessionEvent;
import ymsg.network.event.SessionExceptionEvent;
import ymsg.network.event.SessionFileTransferEvent;
import ymsg.network.event.SessionFriendEvent;
import ymsg.network.event.SessionListener;
import ymsg.network.event.SessionNewMailEvent;
import ymsg.network.event.SessionNotifyEvent;
import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;
import abstractionLayer.Status;

public class YahooAccount implements AbstractAccount, SessionListener, HackListen {

	protected AccountSettings theSettings;
	protected IMEvents theEvents;
	
	protected Session myCon;
	
	protected boolean isConnected;
	
	protected IgnoreList ignoreNext;
	
	
	public YahooAccount() {
		myCon = new Session();
		isConnected = false;
		ignoreNext = new IgnoreList();
	}
	
	
	public void addBuddy(Buddy theBuddy) {
		try {
			myCon.addFriend(theBuddy.getScreename(), theBuddy.getGroupName());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void connect() {
		//System.out.println("Connecting");
		myCon.setHack(this);
		myCon.addSessionListener(this);
		//System.out.println("Set stuff");
		try {
			//System.out.println("About to login...");
			myCon.login(theSettings.getUsername(), theSettings.getPassword());
			//System.out.println("Logged in");
			myCon.refreshFriends();
			//System.out.println("Refreshed");
		} catch (AccountLockedException e) {
			theEvents.loginError(this);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (LoginRefusedException e) {
			theEvents.loginError(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isConnected = true;
		
		// for whatever reason, we don't get a listReceived event
		// they purposly don't give us one..
		// make our own!
		listReceived(null);
		//System.out.println("connect out");
	}

	public void disconnect() {
		try {
			myCon.logout();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public AccountSettings getAccountSettings() { return theSettings; }

	public boolean isConnected() {
		return isConnected;
	}

	public void sendIM(IM theIM) { 
		try {
			myCon.sendMessage(theIM.to, theIM.message);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAccountSettings(AccountSettings as) { theSettings = as; }

	public void setListener(IMEvents theEvent) {
		// note the 's"
		theEvents = theEvent;
	}

	public void setStatus(Status theStatus) {
		// if we have a custom status message, we are either away or here.
		// if we don't, we can be a whole lot of things.
		
		try {

			if (theStatus.getStatusMessage() == null) {
				if (theStatus.getStatus().equals(Status.available) || theStatus.getStatus().equals(Status.superAvailable)) {
					myCon.setStatus(StatusConstants.STATUS_AVAILABLE);
				} else if (theStatus.getStatus().equals(Status.away)) {
					myCon.setStatus(StatusConstants.STATUS_BRB);
				} else if (theStatus.getStatus().equals(Status.superAway)) {
					myCon.setStatus(StatusConstants.STATUS_STEPPEDOUT);
				} else if (theStatus.getStatus().equals(Status.doNotDistrub)) {
					myCon.setStatus(StatusConstants.STATUS_BUSY);
				} else {
					System.out.println("Got unknown status in Yahoo: " + theStatus.getStatus());
				}
			} else {
				// it is a custom status.
				boolean b = (theStatus.getStatus().equals(Status.available) || theStatus.getStatus().equals(Status.superAvailable));
				myCon.setStatus(theStatus.getStatusMessage(), b);
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO these methods need code
	// but as of right now, our framework isn't expanded enough for it.
	public void buzzReceived(SessionEvent arg0) { }
	public void chatCaptchaReceived(SessionChatEvent arg0) { }
	public void chatConnectionClosed(SessionEvent arg0) { }
	public void chatLogoffReceived(SessionChatEvent arg0) { }
	public void chatLogonReceived(SessionChatEvent arg0) { }
	public void chatMessageReceived(SessionChatEvent arg0) { }
	public void chatUserUpdateReceived(SessionChatEvent arg0) { }
	public void conferenceInviteDeclinedReceived(SessionConferenceEvent arg0) { }
	public void conferenceInviteReceived(SessionConferenceEvent arg0) { }
	public void conferenceLogoffReceived(SessionConferenceEvent arg0) { }
	public void conferenceLogonReceived(SessionConferenceEvent arg0) { }
	public void conferenceMessageReceived(SessionConferenceEvent arg0) { }
	public void contactRejectionReceived(SessionEvent arg0) { }
	public void contactRequestReceived(SessionEvent arg0) { }
	public void errorPacketReceived(SessionErrorEvent arg0) { }
	public void fileTransferReceived(SessionFileTransferEvent arg0) { }
	public void friendAddedReceived(SessionFriendEvent arg0) { }
	public void inputExceptionThrown(SessionExceptionEvent arg0) {}
	public void newMailReceived(SessionNewMailEvent arg0) { }
	
	public void connectionClosed(SessionEvent arg0) {
		isConnected = false;
	}
	
	public void friendsUpdateReceived(SessionFriendEvent arg0) {
		
		Buddy myBuddy = new Buddy();
		
		YahooUser myYU = arg0.getFriend();
		
		//System.out.println("Got update: " + myYU.toString());
		
		myBuddy = this.YahooUserToJimBuddy(myYU);
		
		if (ignoreNext.checkUser(myBuddy.getScreename())) { return; }
		
		myBuddy.setGroupName(arg0.getGroup());
		theEvents.buddyStatusChange(myBuddy, false);
	}

	


	@SuppressWarnings("unchecked")
	// surpressing the setting of the theUsers variable... we know we are getting data of type YahooUser
	public void listReceived(SessionEvent arg0) {
		
		Buddy myBuddy;
		ArrayList<YahooUser> theUsers;
		String groupName = "";
		
		
		// we'll iterate through the values
		YahooGroup[] theGroups = myCon.getGroups();
		
		for (YahooGroup myYG : theGroups) {
			theUsers = Collections.list(myYG.getMembers().elements());
			groupName = myYG.getName();
			
			for (YahooUser myYU : theUsers) {
				if (myYU.isFriend()) {
					myBuddy = this.YahooUserToJimBuddy(myYU);
					
					if (!(ignoreNext.checkUser(myBuddy.getScreename()))) {
						//System.out.println("Updating: " + myBuddy.getScreename());
						myBuddy.setGroupName(groupName);
						theEvents.buddyStatusChange(myBuddy, false);
					}
				}
			}
		}
		
		
	
	}

	public void messageReceived(SessionEvent arg0) {
		IM myIM = new IM();
		
		myIM.theAccount = this;
		myIM.to = arg0.getTo();
		myIM.from = arg0.getFrom();
		myIM.message = arg0.getMessage();
		
		theEvents.gotIM(myIM);
		
	}


	// TODO typing and game notifications
	public void notifyReceived(SessionNotifyEvent arg0) { }

	public void offlineMessageReceived(SessionEvent arg0) {
		IM myIM = new IM();
		
		myIM.theAccount = this;
		myIM.to = arg0.getTo();
		myIM.from = arg0.getFrom();
		myIM.message = arg0.getMessage();
		myIM.offline = true;
		
		theEvents.gotIM(myIM);
	}

	
	protected Buddy YahooUserToJimBuddy(YahooUser myYU) {
		//System.out.println(myYU.toString());
		
		Buddy myBuddy = new Buddy();
		myBuddy.setAccount(this);
		myBuddy.setScreename(myYU.getId());
		
		//System.out.println("Parsing user: " + myBuddy.getScreename());
		
		
		Status toSet = new Status();
		
		if (myYU.getStatus() == StatusConstants.STATUS_AVAILABLE) {
			toSet.setStatus(Status.available);
		} else if (myYU.getStatus() == StatusConstants.STATUS_BRB || myYU.getStatus() == StatusConstants.STATUS_NOTATDESK || myYU.getStatus() == StatusConstants.STATUS_NOTATHOME || myYU.getStatus() == StatusConstants.STATUS_NOTINOFFICE || myYU.getStatus() == StatusConstants.STATUS_ONPHONE || myYU.getStatus() == StatusConstants.STATUS_OUTTOLUNCH || myYU.getStatus() == StatusConstants.STATUS_STEPPEDOUT) {
			toSet.setStatus(Status.away);
		} else if (myYU.getStatus() == StatusConstants.STATUS_ONVACATION) {
			toSet.setStatus(Status.superAway);
		} else if (myYU.getStatus() == StatusConstants.STATUS_BUSY) {
			toSet.setStatus(Status.doNotDistrub);
		} else if (myYU.getStatus() == StatusConstants.STATUS_CUSTOM) {
			if (myYU.isCustomBusy()) {
				toSet.setStatus(Status.away);
			} else {
				toSet.setStatus(Status.available);
			}
			
			toSet.setStatusMessage(myYU.getCustomStatusMessage());
		
		} else if (myYU.getStatus() == StatusConstants.STATUS_OFFLINE) {
			toSet.setStatus(Status.offline);
		} else {
			System.out.println("Got unknown status in Yahoo: " + myYU.getStatus());
		}
		
		myBuddy.setStatus(toSet);
		
		// We've got to set the alias to something
		// so that we check to see if we have a saved one
		myBuddy.setAlias(null);
		
		return myBuddy;
	}
	
	public void friendRemovedReceived(SessionFriendEvent arg0) { 
		Buddy myBuddy = new Buddy();
		
		YahooUser myYU = arg0.getFriend();
		
		myBuddy = this.YahooUserToJimBuddy(myYU);
		//myBuddy.setGroupName(arg0.getGroup()); not needed to remove
		
		theEvents.buddyDeleted(myBuddy);
	}


	public void gotPacket(YMSG9Packet arg0) {
		
		//System.out.println(arg0.toString());
		
		// because the API is kind of broken, we've got to play
		// some games with this.
		
		// FOR INTIAL:
		
		// we don't capture custom status messages on login properly.
		// we need to check for that here
		// but if they don't have a custom status
		// we're fine!
		
		// status updates seem to be sent with the following properties
		// Service is 1
		// Status is 0
		// body[0] is 0
		// body[1] is the screenname (might not be -- don't count on it
		// body[7] is their username
		// body[15] is a custom status if they have one, otherwise it is 1
		// body[16] is a status... looks like 138 for here, 47 for otherwise
		
		if (arg0.service == 1 && arg0.status == 0 && arg0.body[0].equals("0")) {
			// stop right now if they don't have a custom status
			if (arg0.body[15].equals("1")) return;
			
			System.out.println(arg0.toString());
			
			// make sure we don't wipe out this value when the broken event comes in
			ignoreNext.addUser(arg0.body[7]);
			
			// and, we'll also get a friendsUpdate event, so we need to ignore both
			// the list rec'd event and that one
			
			ignoreNext.addUser(arg0.body[7]);
			
			Buddy b = new Buddy();
			b.setAccount(this);
			b.setScreename(arg0.body[7]);
			Status toSet = new Status();
			toSet.setStatus((arg0.body[16].equals("138") ? Status.available : Status.away));
			toSet.setStatusMessage(arg0.body[15]);
			
			b.setStatus(toSet);
			
			theEvents.buddyStatusChange(b, false);
			return;		
		}
		
		// FOR UPDATES:
		// custom statuses appear to, for lack of better words, "not work"
		// We seem to get the following when there is a status change:
		
		// Status is 1
		// body[0] is 7
		// body[1] is the username who's status is changing
		// body[7] is the status message
		// body[9] is 1 if away, 0 if avail.
		
		if (arg0.status == 1 && arg0.body[0].equals("7")) {
			Buddy b = new Buddy();
			
			b.setAccount(this);
			b.setScreename(arg0.body[1]);
			
			Status toSet = new Status();
			
			toSet.setStatus((arg0.body[9].equals("0") ? Status.available : Status.away));
			toSet.setStatusMessage(arg0.body[7]);
			
			
			b.setStatus(toSet);
			
			theEvents.buddyStatusChange(b, false);
			
			return;
		}
		
	}
}
