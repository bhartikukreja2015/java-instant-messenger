package gtalkStuff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.net.SocketFactory;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
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
		myConfig.setSocketFactory(SocketFactory.getDefault());
		myConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
		myConfig.setKeystoreType("jks");
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
				myBuddy.setScreename(re.getUser());
				myBuddy.setAlias(re.getName());
				myBuddy.setOnlineStatus(false);

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
		if (!arg1) { arg0.addMessageListener(this); }
		theChats.add(arg0);
	}

	public void processMessage(Chat arg0, Message arg1) {
		if (arg1.getBody() == null) { return; }
		
		IM myIM = new IM();
		myIM.automatic = false;
		myIM.from = arg0.getParticipant();
		myIM.message = arg1.getBody();
		myIM.theAccount = this;
		
		theEvents.gotIM(myIM);
	}

	public void entriesAdded(Collection<String> arg0) {
		System.out.println("Added: " + arg0);
		
	}

	public void entriesDeleted(Collection<String> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void entriesUpdated(Collection<String> arg0) {
		System.out.println("Updated: " + arg0);
		
	}

	public void presenceChanged(Presence arg0) {
		Buddy myBuddy = new Buddy();
		
		myBuddy.setScreename(arg0.getFrom());
		
		myBuddy.setOnlineStatus(arg0.isAvailable());
		
		myBuddy.setAccount(this);
		
		if (arg0.getMode() == Presence.Mode.available) {
			myBuddy.setStatus(Buddy.available);
		} else if (arg0.getMode() == Presence.Mode.away) {
			myBuddy.setStatus(Buddy.away);
		} else if (arg0.getMode() == Presence.Mode.xa) {
			myBuddy.setStatus(Buddy.superAway);
		} else if (arg0.getMode() == Presence.Mode.dnd) {
			myBuddy.setStatus(Buddy.DoNotDistrub);
		} else if (arg0.getMode() == Presence.Mode.chat) {
			myBuddy.setStatus(Buddy.superAvailable);
		}
		
		theEvents.buddyStatusChange(myBuddy, false);
		
	}

	public void sendIM(IM theIM) {
		
		for (Chat c : theChats) {
			if (c.getParticipant() == theIM.from) {
				try {
					c.sendMessage(theIM.message);
				} catch (XMPPException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		
		// if we are still here, we need to create the chat.
		myCon.getChatManager().createChat(theIM.from, this);
		// we've opened the chat, so we'll add it to the list.
		// send through the right chat.
		
		sendIM(theIM);
	}

	public void setStatus(Buddy theStatus) {
		Presence myPresence = new Presence();
		
	}



}
