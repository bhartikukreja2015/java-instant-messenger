package notificationStuff;

public class Notification {
	public static int GotIM = 0;
	public static int BuddyOnline = 1;
	public static int BuddyOffline = 2;
	public static int BuddyStatusChange = 3;
	public static int ConnectingAccount = 4;
	public static int AccountConnected = 5;
	public static int AccountDisconnected = 6;
	
	protected int type;
	
	protected String subject;
	protected String message;
	protected String iconHint;
	
	public Notification(int theType) { type = theType; }
	public Notification() {};
	
	public void setType(int t) { type = t; }
	public int getType() { return type; }
	
	public void setSubject(String s) { subject = s; }
	public void setMessage(String m) { message = m; }
	public void setIconHint(String h) { iconHint = h; }
	
	public String getSubject() { return subject; }
	public String getMessage() { return message; }
	public String getIconHint() { return iconHint; }
		
	public void dispatch() {
			NotificationDispatcher myND = new NotificationDispatcher();
			myND.dispatchNotification(this);
	}
	
	public String toString() {
		return "<[Subject: " + this.getSubject() + "][Message: " + this.getMessage() + "][Icon: " + this.getIconHint() + "]>";
	}
	
}
