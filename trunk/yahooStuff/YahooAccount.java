package yahooStuff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import ymsg.network.AccountLockedException;
import ymsg.network.LoginRefusedException;
import ymsg.network.Session;
import ymsg.network.StatusConstants;
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

public class YahooAccount implements AbstractAccount, SessionListener {

	protected AccountSettings theSettings;
	protected IMEvents theEvents;
	
	protected Session myCon;
	
	protected boolean isConnected;
	
	
	public YahooAccount() {
		myCon = new Session();
		isConnected = false;
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
		myCon.addSessionListener(this);
		try {
			myCon.login(theSettings.getUsername(), theSettings.getPassword());
			myCon.refreshFriends();
		} catch (AccountLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginRefusedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isConnected = true;
		
		// for whatever reason, we don't get a listReceived event
		// they purposly don't give us one..
		// make our own!
		listReceived(null);
		
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

	public void setStatus(Buddy theStatus) {
		// if we have a custom status message, we are either away or here.
		// if we don't, we can be a whole lot of things.
		
		try {

			if (theStatus.getStatusMessage() == null) {
				if (theStatus.getStatus().equals(Buddy.available) || theStatus.getStatus().equals(Buddy.superAvailable)) {
					myCon.setStatus(StatusConstants.STATUS_AVAILABLE);
				} else if (theStatus.getStatus().equals(Buddy.away)) {
					myCon.setStatus(StatusConstants.STATUS_BRB);
				} else if (theStatus.getStatus().equals(Buddy.superAway)) {
					myCon.setStatus(StatusConstants.STATUS_STEPPEDOUT);
				} else if (theStatus.getStatus().equals(Buddy.doNotDistrub)) {
					myCon.setStatus(StatusConstants.STATUS_BUSY);
				} else {
					System.out.println("Got unknown status in Yahoo: " + theStatus.getStatus());
				}
			} else {
				// it is a custom status.
				boolean b = (theStatus.getStatus().equals(Buddy.available) || theStatus.getStatus().equals(Buddy.superAvailable));
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
		
		myBuddy = this.YahooUserToJimBuddy(myYU);
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
					myBuddy.setGroupName(groupName);
					theEvents.buddyStatusChange(myBuddy, false);
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
		Buddy myBuddy = new Buddy();
		myBuddy.setAccount(this);
		myBuddy.setScreename(myYU.getId());
		
		if (myYU.getStatus() == StatusConstants.STATUS_AVAILABLE) {
			myBuddy.setStatus(Buddy.available);
		} else if (myYU.getStatus() == StatusConstants.STATUS_BRB || myYU.getStatus() == StatusConstants.STATUS_NOTATDESK || myYU.getStatus() == StatusConstants.STATUS_NOTATHOME || myYU.getStatus() == StatusConstants.STATUS_NOTINOFFICE || myYU.getStatus() == StatusConstants.STATUS_ONPHONE || myYU.getStatus() == StatusConstants.STATUS_OUTTOLUNCH || myYU.getStatus() == StatusConstants.STATUS_STEPPEDOUT) {
			myBuddy.setStatus(Buddy.away);
		} else if (myYU.getStatus() == StatusConstants.STATUS_ONVACATION) {
			myBuddy.setStatus(Buddy.superAway);
		} else if (myYU.getStatus() == StatusConstants.STATUS_BUSY) {
			myBuddy.setStatus(Buddy.doNotDistrub);
		} else if (myYU.getStatus() == StatusConstants.STATUS_CUSTOM) {
			if (myYU.isCustomBusy()) {
				myBuddy.setStatus(Buddy.away);
			} else {
				myBuddy.setStatus(Buddy.available);
			}
			
			myBuddy.setStatusMessage(myYU.getCustomStatusMessage());
		} else {
			System.out.println("Got unknown status in Yahoo: " + myYU.getStatus());
		}
		
		myBuddy.setOnlineStatus(!(myYU.getStatus() == StatusConstants.STATUS_OFFLINE));
		
		return myBuddy;
	}
	
	public void friendRemovedReceived(SessionFriendEvent arg0) { 
		Buddy myBuddy = new Buddy();
		
		YahooUser myYU = arg0.getFriend();
		
		myBuddy = this.YahooUserToJimBuddy(myYU);
		//myBuddy.setGroupName(arg0.getGroup()); not needed to remove
		
		theEvents.buddyDeleted(myBuddy);
	}
}
