package abstractionLayer;

import jimPreferences.PreferencePoint;

public class Buddy implements Comparable<Object> {	
	public boolean saveAlias = true;
	
	protected String screenname;
	protected String alias;
	protected String groupName;
	protected int mergeID;
	protected int mergePriority;
	
	protected Status theStatus;
	
	protected AbstractAccount theAccount;
	protected String resource;
	
	protected boolean shouldShow;
	
	public Buddy(String sn, String groupname, int theMergeID) {
		screenname = sn;
		groupName = groupname;
		mergeID = theMergeID;
		theStatus = new Status();
	}
	
	public Buddy() {
		theStatus = new Status();
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
	
	public boolean isOnline() { return theStatus.isOnline(); }
	
	public void setStatus(Status pStatus) { theStatus = pStatus; }
	public Status getStatus() { return theStatus; }
	
	// by default, this method will NOT override a value we've already saved.
	public void setAlias(String a) { 
		setAlias(a, false);
	}
	
	public void setAlias(String a, boolean overwriteSaved) { 
		//System.out.println("Setting alias to: " + a);
		System.out.println("1");
		PreferencePoint PP = new PreferencePoint();
		System.out.println("2");
		if (saveAlias) {
			System.out.println("3");
			if (!overwriteSaved) {
				System.out.println("4");
				// see if we already have a saved value...
				String saved = PP.getAliasForScreenname(screenname, this.getAccount().getAccountSettings().getUsername());
				System.out.println("5");
				if (saved != null) {
					System.out.println("6");
					alias = saved;
					//System.out.println("Overwritting " + a + " for saved: " + saved);
					return;
				}
			}
			
			System.out.println("7");
			
			// no alias was saved or we are overwritting, so save this one...
			// if the sent alias wasn't null
			if (a != null) {
				System.out.println("8");
				PP.setAliasForScreenname(screenname, theAccount.getAccountSettings().getUsername(), a);
				alias = a;
			}
			
			System.out.println("9");
		}
		
		// No longer needed: Get merge ID checks.
		//mergeID = this.checkForSavedMergeID();
		
		
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
		
		//System.out.println(this.getScreename() + "|" + b.getScreename());
		//System.out.println(this.getStatus().getStatus() + "|" + b.getStatus().getStatus());
		
		
		if (this.getStatus().getStatus().equals(Status.available)) {
			if (b.getStatus().getStatus().equals(Status.available)) {
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
	
	public boolean isShowing() { return shouldShow; }
	public void setShow(boolean b) { shouldShow = b; }
}
