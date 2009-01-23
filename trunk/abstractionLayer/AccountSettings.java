package abstractionLayer;

public class AccountSettings {
	protected String username;
	protected String password;
	protected String accountType;
	protected int accountID;
	
	public static final String GoogleTalkAccount = "gtalk";
	public static final String AIMAccount = "aim";
	public static final String YahooAccount = "yim";
	public static final String MSNAccount = "msn";
	
	public AccountSettings(String uname, String pword) {
		username = uname;
		password = pword;
	}
	
	public AccountSettings() {
		// do nothing
	}
	
	public String getUsername() { return username; }
	public void setUsername(String uname) { username = uname; }
	
	public String getPassword() { return password; }
	public void setPassword(String pword) { password = pword; }
	
	public String getAccountType() { return accountType; }
	public void setAccountType(String theString) { accountType = theString; }
	
	public int getID() { return accountID; }
	public void setID(int id) { accountID = id; }
}
