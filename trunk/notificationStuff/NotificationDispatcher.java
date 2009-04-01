package notificationStuff;

import java.io.IOException;

import mainIconSet.IconFetch;
import abstractionLayer.Status;

public class NotificationDispatcher {
	public static String Ubuntu = "Linux";
	public static String MacOSX = "Mac OS X";
	public static String Windows = "Windows";
	
	protected String os;
	
	public NotificationDispatcher() { os = System.getProperty("os.name"); }

	public void dispatchNotification(Notification theNot) {
		System.out.println("On OS: " + os);
		
		if (os.equals("Linux")) {
			// use notify-send
			StringBuilder theSB = new StringBuilder();
			theSB.append("notify-send --category=");
			
			IconFetch theFetch = new IconFetch();
			
			if (theNot.getType() == Notification.GotIM) {
				theSB.append("im.received --icon==");
				theSB.append(theFetch.getURL(Status.superAvailable));
			} else {
				theSB.append("im --icon==");
				
				// TODO at some point, we should get the exact statuses....
				if (theNot.getType() == Notification.BuddyOffline) {
					theSB.append(theFetch.getURL(Status.offline));
				} else if (theNot.getType() == Notification.BuddyOnline) {
					theSB.append(theFetch.getURL(Status.available));
				} else {
					theSB.append(theFetch.getURL(Status.away));
				}
			}
			
			theSB.append(" '");
			theSB.append(theNot.getSubject());
			theSB.append("' '");
			theSB.append(theNot.getMessage());
			theSB.append("'");
			
			runShellCommand(theSB.toString());
			System.out.println(theSB.toString());
			
		}
	}
	
	protected void runShellCommand(String command) {
		// use notify-send
		Runtime run = Runtime.getRuntime();
		@SuppressWarnings("unused")
		// we don't need to read pr because we only care that we run the command.
		Process pr;
		try {
			pr = run.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}