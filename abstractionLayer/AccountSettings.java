package abstractionLayer;

public class AccountSettings {
	protected String username;
	protected String password;
	protected String accountType;
	protected String accountAlias;
	protected boolean enabled;
	protected int accountID;
	
	public static final String GoogleTalkAccount = "Google Talk";
	public static final String AIMAccount = "AOL";
	public static final String YahooAccount = "Yahoo";
	public static final String MSNAccount = "MSN";
	
	public AccountSettings(String uname, String pword) {
		username = uname;
		password = pword;
		enabled = false;
	}
	
	public AccountSettings() {
		enabled = false;
	}
	
	public String getUsername() { return username; }
	public void setUsername(String uname) { username = uname; }
	
	public String getPassword() { return password; }
	public void setPassword(String pword) { password = pword; }
	
	public String getAccountType() { return accountType; }
	public void setAccountType(String theString) { accountType = theString; }
	
	public int getID() { return accountID; }
	public void setID(int id) { accountID = id; }
	
	public boolean isEnabled() { return enabled; }
	public void setEnabled(boolean b) { enabled = b; }
	
	public String getAlias() { return accountAlias; }
	public void setAlias(String ta) { accountAlias = ta; }
	
	public Buddy getMyself() {
		Buddy b = new Buddy();
		b.setScreename(username);
		b.directSetAlias(accountAlias);
		
		return b;
	}
}
