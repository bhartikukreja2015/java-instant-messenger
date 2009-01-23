package abstractionLayer;

public interface IMEvents {
	public void gotIM(IM theIM);
	
	public void buddyStatusChange(Buddy theBuddy, boolean firstTime);
	
	public void loggedIn(AbstractAccount theAccount);
}
