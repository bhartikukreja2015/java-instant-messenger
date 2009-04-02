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
		
		if (os.equals("Linux")) {
			
			//System.out.println("Linux: " + os);
			// use notify-send
			String[] theCMD = new String[5];
			theCMD[0] = "/usr/bin/notify-send";
			
			if (theNot.getType() == Notification.GotIM) {
				theCMD[1] = "--category=im.received";
				theCMD[2] = "--icon=" + this.getImage(Status.superAvailable);
			} else {
				theCMD[1] = "--category=im";
				theCMD[2] = "--icon=" + this.getImage(theNot.getIconHint());
			}
			
			theCMD[3] = theNot.getSubject();
			theCMD[4] = theNot.getMessage();
			
			runShellCommand(theCMD);
		}
		

	}
	
	protected void runShellCommand(String[] command) {
		Runtime run = Runtime.getRuntime();
		
		Process pr;
		try {
			pr = run.exec(command);
			pr.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String getImage(String hint) {
		IconFetch theFetch = new IconFetch();
		
		String theURL = theFetch.getURL(hint, true);
		theURL = theURL.substring(5);
		theURL = this.nonRegExReplaceAll(theURL, "%20", " ");
		
		return theURL;
		
	}
	
	// TODO learn regex and fix this.
	private String nonRegExReplaceAll(String haystack, String needle, String replace) {
		if (haystack.length() < needle.length()) return haystack;
		
		if (haystack.substring(0, needle.length()).equals(needle)) {
			return replace + nonRegExReplaceAll(haystack.substring(3), needle, replace);
		}
		
		return haystack.substring(0, 1) + nonRegExReplaceAll(haystack.substring(1), needle, replace);
			
		
	}
}
