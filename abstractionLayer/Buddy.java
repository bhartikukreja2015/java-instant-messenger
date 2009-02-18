package abstractionLayer;

import jimPreferences.PreferencePoint;

public class Buddy implements Comparable<Object> {
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
	protected int mergePriority;
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
	
	public void setMergeID(int mi) { 
		mergeID = mi;
		PreferencePoint PP = new PreferencePoint();
		PP.setMergeIDForScreenname(this.getScreename(), this.getAccount().getAccountSettings().getUsername(), mi);
	}
	public int getMergeID() { 
		int i = this.checkForSavedMergeID();
		
		
		if (mergeID != i) {
			mergeID = i;
		}
		
		return mergeID;
	}
	
	public void setMergePrioroity(int mp) { mergePriority = mp; }
	public int getMergePrioroity() { return mergePriority; }
	
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
		//System.out.println("Setting alias to: " + a);
		
		PreferencePoint PP = new PreferencePoint();
		
		if (saveAlias) {
			if (!overwriteSaved) {
				// see if we already have a saved value...
				String saved = PP.getAliasForScreenname(screenname, theAccount.getAccountSettings().getUsername());
				if (saved != null) {
					alias = saved;
					//System.out.println("Overwritting " + a + " for saved: " + saved);
					return;
				}
			}
			
			// no alias was saved or we are overwritting, so save this one...
			// if the sent alias wasn't null
			if (a != null) {
				PP.setAliasForScreenname(screenname, theAccount.getAccountSettings().getUsername(), a);
			}
		}
		
		// muhahaha... sleezy strategy, but let's go ahead and
		// check for a merge ID here because all of our accounts call it
		// and no accounts grant a merge id.
		mergeID = this.checkForSavedMergeID();
		
		alias = a;
	}
	
	public void directSetAlias(String a) { alias = a; }
	
	public String getAlias() { return alias; }
	
	public void setAccount(AbstractAccount aa) { theAccount = aa; }
	public AbstractAccount getAccount() { return theAccount; }
	
	public void setResource(String r) { resource = r; }
	public String getResource() { return resource; }

	protected int checkForSavedMergeID() {
		PreferencePoint PP = new PreferencePoint();
		return PP.getMergeIDForScreenname(this.getScreename(), this.getAccount().getAccountSettings().getUsername());
	}
	
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof Buddy)) { return 0; } // what the hell? Not a buddy?
		
		Buddy b = (Buddy) arg0;
		
		if (this.getStatus().equals(Buddy.available)) {
			if (b.getStatus().equals(Buddy.available)) {
				return 0; // we are both available
			}
			return -1; // we are available, they are not!
		}
		
		if (this.isOnline()) {
			if (b.isOnline()) {
				return 0; // we are both online and not ava
			}
			
			return -1; // they are offline and we are online
		}
		
		if (!this.isOnline() && !b.isOnline()) {
			return 0; // we are both offline
		}
		
		return 1; // we must be less then them
	}
}
