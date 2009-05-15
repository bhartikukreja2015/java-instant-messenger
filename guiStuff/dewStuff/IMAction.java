package guiStuff.dewStuff;

import guiStuff.imWindowStuff.IMWindowManager;

import java.awt.Image;

import mainIconSet.IconFetch;

import jimPreferences.PreferencePoint;

import abstractionLayer.Buddy;
import abstractionLayer.Status;

public class IMAction implements Action {

	protected IMWindowManager theImwm;
	protected Buddy ourBuddy;
	
	public IMAction (IMWindowManager theM, Buddy theB) {
		theImwm = theM;
		ourBuddy = theB;
	}
	
	public void execute() {
		theImwm.createIMWindow(ourBuddy);
		
	}

	public String getDesc() {
		return "Send an IM to " + ourBuddy.getAlias() + " (" + ourBuddy.getScreename() + ")";
	}


	public Image getImage() {
		PreferencePoint pp = new PreferencePoint();
		IconFetch myFetch = new IconFetch(pp.getIconTheme());
		return myFetch.loadImage(Status.superAvailable);
	}


	public String getName() {
		return "IM with " + ourBuddy.getAlias();
	}

}
