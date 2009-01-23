package abstractionLayer;

public interface AbstractAccount {
	public void setAccountSettings(AccountSettings as);
	public AccountSettings getAccountSettings();
	
	public void connect();
	public void disconnect();
	public boolean isConnected();
	
	public void addBuddy(Buddy theBuddy);
	
	public void setListener(IMEvents theEvent);
	
	public void sendIM(IM theIM);
}
