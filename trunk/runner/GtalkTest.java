package runner;

import gtalkStuff.GTalkAccount;
import abstractionLayer.AbstractAccount;
import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;
import abstractionLayer.IM;
import abstractionLayer.IMEvents;

public class GtalkTest implements IMEvents {
	AbstractAccount myGtalk;
	AccountSettings myAS;
	
	public static void main(String[] args) {
		GtalkTest myTest = new GtalkTest();
		myTest.doIt();
	}
	
	public void doIt() {
		myGtalk = new GTalkAccount();
		myAS = new AccountSettings("allthingsblizzard@gmail.com", "spruce1541!");
		
		myGtalk.setAccountSettings(myAS);
		myGtalk.setListener(this);
		myGtalk.connect();
		System.out.println("Started");
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void buddyStatusChange(Buddy theBuddy) {
		System.out.println("Got update: " + theBuddy.getScreename());
		
	}

	public void gotIM(IM theIM) {
		System.out.println("Got IM from  " + theIM.from + ": " + theIM.message);
		
		IM myIM = new IM();
		myIM.from = theIM.from;
		myIM.message = theIM.message;
		
		myGtalk.sendIM(myIM);
		
	}

	public void loggedIn() {
		System.out.println("Logged in");
		
	}
	
	
	
}
