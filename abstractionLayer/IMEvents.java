package abstractionLayer;

public interface IMEvents {
	public void gotIM(IM theIM);
	
	public void buddyStatusChange(Buddy theBuddy);
	
	public void loggedIn();
}
