package abstractionLayer;

public interface IMEvents {
	public void gotIM(IM theIM);
	
	public void buddyStatusChange(Buddy theBuddy, boolean firstTime);
	public void buddyDeleted(Buddy theBuddy);
	
	public void loggedIn(AbstractAccount theAccount);
	public void loginError(AbstractAccount theAccount);
}
