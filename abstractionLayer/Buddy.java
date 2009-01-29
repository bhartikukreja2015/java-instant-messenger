package abstractionLayer;

import jimPreferences.PreferencePoint;

public class Buddy {
	public static final String available = "Available";
	public static final String superAvailable = "Chatty";
	public static final String away = "Away";
	public static final String superAway = "Really away";
	public static final String doNotDistrub = "Do not distrub";
	public static final String offline = "Offline";
	
	public boolean saveAlias = true;
	
	protected String screenname;
	protected String alias;
	protected String groupName;
	protected int mergeID;
	protected boolean onlineStatus;
	protected String status;
	protected String statusMessage;
	protected AbstractAccount theAccount;
	protected String resource;
	
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
	
	public void setOnlineStatus(boolean online) { 
		onlineStatus = online;
		if (!online) {
			setStatus(Buddy.offline);
		}
	}
	public boolean isOnline() { return onlineStatus; }
	
	public void setStatus(String theStatus) { status = theStatus; }
	public String getStatus() { return status; }
	
	public void setStatusMessage(String theStatusMessage) { statusMessage = theStatusMessage; }
	public String getStatusMessage() { return statusMessage; }
	
	// by default, this method will NOT override a value we've already saved.
	public void setAlias(String a) { 
		setAlias(a, false);
	}
	
	public void setAlias(String a, boolean overwriteSaved) { 
		PreferencePoint PP = new PreferencePoint();
		
		if (saveAlias) {
			if (!overwriteSaved) {
				// see if we already have a saved value...
				String saved = PP.getAliasForScreenname(screenname, theAccount.getAccountSettings().getUsername());
				if (saved != null) {
					alias = saved;
					return;
				}
			}
			
			// no alias was saved or we are overwritting, so save this one...
			PP.setAliasForScreenname(screenname, theAccount.getAccountSettings().getUsername(), a);
		}
		
		alias = a;
	}
	public String getAlias() { return alias; }
	
	public void setAccount(AbstractAccount aa) { theAccount = aa; }
	public AbstractAccount getAccount() { return theAccount; }
	
	public void setResource(String r) { resource = r; }
	public String getResource() { return resource; }
}
