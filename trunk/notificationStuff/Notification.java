package notificationStuff;

public class Notification {
	public static int GotIM = 0;
	public static int BuddyOnline = 1;
	public static int BuddyOffline = 2;
	public static int BuddyStatusChange = 3;
	
	protected int type;
	
	protected String subject;
	protected String message;
	
	public Notification(int theType) { type = theType; }
	public Notification() {};
	
	public void setType(int t) { type = t; }
	public int getType() { return type; }
	
	public void setSubject(String s) { subject = s; }
	public void setMessage(String m) { message = m; }
	
	public String getSubject() { return subject; }
	public String getMessage() { return message; }
	
	public void dispatch() {
			NotificationDispatcher myND = new NotificationDispatcher();
			myND.dispatchNotification(this);
	}
	
}
