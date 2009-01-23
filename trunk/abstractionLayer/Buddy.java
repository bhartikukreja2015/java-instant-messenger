package abstractionLayer;

public class Buddy {
	public static final String available = "Available";
	public static final String superAvailable = "Chatty";
	public static final String away = "Away";
	public static final String superAway = "Really away";
	public static final String DoNotDistrub = "Do not distrub";
	
	
	protected String screenname;
	protected String alias;
	protected String groupName;
	protected int mergeID;
	protected boolean onlineStatus;
	protected String status;
	protected String statusMessage;
	
	public Buddy(String sn, String groupname, int theMergeID) {
		screenname = sn;
		groupName = groupname;
		mergeID = theMergeID;
	}
	
	public Buddy(String sn, boolean isOnline) {
		screenname = sn;
		onlineStatus = isOnline;
	}
	
	public Buddy() {
		
	}
	
	public void setScreename(String sn) { screenname = sn; }
	public String getScreename() { return screenname; }
	
	public void setGroupName(String gn) { groupName = gn; }
	public String getGroupName() { return groupName; }
	
	public void setMergeID(int mi) { mergeID = mi; }
	public int getMergeID() { return mergeID; }
	
	public void setOnlineStatus(boolean online) { onlineStatus = online; }
	public boolean isOnline() { return onlineStatus; }
	
	public void setStatus(String theStatus) { status = theStatus; }
	public String getStatus() { return status; }
	
	public void setStatusMessage(String theStatusMessage) { statusMessage = theStatusMessage; }
	public String getStatusMessage() { return statusMessage; }
	
	public void setAlias(String a) { alias = a; }
	public String getAlias() { return alias; }
	
}
