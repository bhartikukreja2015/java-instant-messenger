package gtalkStuff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;
import abstractionLayer.Status;

public class GTalkAccount implements AbstractAccount, ChatManagerListener, MessageListener, RosterListener {

	protected XMPPConnection myCon;
	protected ConnectionConfiguration myConfig;
	
	protected AccountSettings theSettings;
	protected IMEvents theEvents;
	
	protected ArrayList<Chat> theChats;
	
	
	
	
	public void addBuddy(Buddy theBuddy) {
		try {
			myCon.getRoster().createEntry(theBuddy.getScreename(), theBuddy.getAlias(), null);
		} catch (XMPPException e) {
			e.printStackTrace();
		}

	}

	public void connect() {
		
		theChats = new ArrayList<Chat>();
		myConfig = new ConnectionConfiguration("talk.google.com", 5222, "gmail.com");
		//myConfig.setSocketFactory(SocketFactory.getDefault());
		//myConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
		//myConfig.setKeystoreType("jks");
		myCon = new XMPPConnection(myConfig);
		try {
			myCon.connect();
			myCon.login(theSettings.getUsername(), theSettings.getPassword(), "Home");
			
			while (!myCon.isAuthenticated()) {
				// block ...
			}
			
			myCon.getChatManager().addChatListener(this);
			myCon.getRoster().addRosterListener(this);
			
			// send the inital buddy list
			ArrayList<RosterEntry> Entries = Collections.list(Collections.enumeration(myCon.getRoster().getEntries()));
			for (RosterEntry re : Entries) {
				Buddy myBuddy = new Buddy();
				myBuddy.setAccount(this);
				myBuddy.setScreename(re.getUser());
				myBuddy.setAlias(re.getName());
				myBuddy.setStatus(new Status(Status.offline));

				theEvents.buddyStatusChange(myBuddy, true);
			}
			
			theEvents.loggedIn(this);
			
			
	
	        
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
	}

	public void disconnect() {
		myCon.disconnect();

	}

	public AccountSettings getAccountSettings() {
		return theSettings;
	}

	public boolean isConnected() {
		return myCon.isConnected();
	}

	public void setAccountSettings(AccountSettings as) {
		theSettings = as;
	}

	public void setListener(IMEvents theEvent) {
		theEvents = theEvent;
	}

	public void chatCreated(Chat arg0, boolean arg1) {
		arg0.addMessageListener(this);
		theChats.add(arg0);
	}

	public void processMessage(Chat arg0, Message arg1) {
		if (arg1.getBody() == null) { return; }
		
		IM myIM = new IM();
		myIM.automatic = false;
		
		// small "gotcha" here...
		// arg0.getParticipant includes the resource
		// but we don't want it included.
		
		// Oh! And there is more fun!
		// The resource is only included if it is the first message.
		// Otherwise its normal.
		// So check to see if we have a "/" before we do anything funny.
		
		String theWhole = arg0.getParticipant();
		
		if (theWhole.indexOf("/") != -1) {
			myIM.from = (theWhole.substring(0, theWhole.indexOf("/")));
		} else {
			myIM.from = theWhole;
		}
		myIM.to = theSettings.getUsername();
		myIM.message = arg1.getBody();
		myIM.theAccount = this;
		
		theEvents.gotIM(myIM);
	}
	
	// TODO trigger buddy list changes for all of these events
	public void entriesAdded(Collection<String> arg0) {
		
	}

	public void entriesDeleted(Collection<String> arg0) {
		
	}

	public void entriesUpdated(Collection<String> arg0) {
		
	}

	public void presenceChanged(Presence arg0) {
		Buddy myBuddy = new Buddy();
		
		// little "gotcha" here... arg0.getFrom includes the resource, but the intial did not.
		// so we have to parse it out.
		
		String theWhole = arg0.getFrom();
		
		myBuddy.setScreename(theWhole.substring(0, theWhole.indexOf("/")));
		myBuddy.setResource(theWhole.substring(theWhole.indexOf("/") + 1));
		
		// we need to get the user's roster entry to get their alias
		Roster theRoster = myCon.getRoster();
		myBuddy.setAlias(theRoster.getEntry(myBuddy.getScreename()).getName());
		
		
		myBuddy.setAccount(this);
		
		Status toSet = new Status();
		
		if (arg0.getMode() == Presence.Mode.available || arg0.getMode() == null) {
			toSet.setStatus(Status.available);
		} else if (arg0.getMode() == Presence.Mode.away) {
			toSet.setStatus(Status.away);
		} else if (arg0.getMode() == Presence.Mode.xa) {
			toSet.setStatus(Status.superAway);
		} else if (arg0.getMode() == Presence.Mode.dnd) {
			toSet.setStatus(Status.doNotDistrub);
		} else if (arg0.getMode() == Presence.Mode.chat) {
			toSet.setStatus(Status.superAvailable);
		}
		
		toSet.setStatusMessage(arg0.getStatus());
		
		
		myBuddy.setStatus(toSet);
		
		
		theEvents.buddyStatusChange(myBuddy, false);
		
	}

	public void sendIM(IM theIM) {
		
		for (Chat c : theChats) {
			if (c.getParticipant().equals(theIM.to)) {
				try {
					c.sendMessage(theIM.message);
				} catch (XMPPException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		
		// if we are still here, we need to create the chat.
		myCon.getChatManager().createChat(theIM.to, this);
		// we've opened the chat, so we'll add it to the list.
		// send through the right chat.
		
		sendIM(theIM);
	}

	public void setStatus(Status theStatus) {
		Presence myPresence = new Presence(Presence.Type.available);
		
		if (theStatus.getStatus() == Status.available) {
			myPresence.setMode(Presence.Mode.available);
		} else if (theStatus.getStatus() == Status.away) {
			myPresence.setMode(Presence.Mode.away);
		} else if (theStatus.getStatus() == Status.doNotDistrub) {
			myPresence.setMode(Presence.Mode.dnd);
		} else if (theStatus.getStatus() == Status.superAvailable) {
			myPresence.setMode(Presence.Mode.chat);
		} else if (theStatus.getStatus() == Status.superAway) {
			myPresence.setMode(Presence.Mode.xa);
		}
		
		myPresence.setStatus(theStatus.getStatusMessage());
		
		myCon.sendPacket(myPresence);
	}





}
