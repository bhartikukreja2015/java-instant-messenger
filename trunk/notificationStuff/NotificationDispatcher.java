package notificationStuff;

public class NotificationDispatcher {
	public static String Ubuntu = "Linux";
	public static String MacOSX = "Mac OS X";
	public static String Windows = "Windows";
	
	protected String os;
	
	public NotificationDispatcher() { os = System.getProperty("os.name"); }
	
	public void dispatchNotification(Notification theNote) {
		
	}
}
