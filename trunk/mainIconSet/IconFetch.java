package mainIconSet;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import abstractionLayer.AccountSettings;
import abstractionLayer.Status;


public class IconFetch {
	
	public static final String faceAngel = "face-angel.png";
	public static final String faceCrying = "face-crying.png";
	public static final String faceDevil = "face-devil-grin.png";
	public static final String faceGlasses = "face-glasses.png";
	public static final String faceGrin = "face-grin.png";
	public static final String faceCoolGuy = "face-guy.png";
	public static final String faceHeart =  "face-heart.png";
	public static final String faceUndecided = "face-hum.png";
	public static final String faceKiss = "face-kiss.png";
	public static final String faceMonkey = "face-monkey.png";	
	public static final String facePlain = "face-plain.png";
	public static final String faceSad = "face-sad.png";
	public static final String faceSmileBig = "face-smile-big.png";
	public static final String faceSmile = "face-smile.png";
	public static final String faceSurprise = "face-surprise.png";
	public static final String faceTongue = "face-tongue.png";
	public static final String faceTongueWink = "face-tonguewink.png";
	public static final String faceWhoa = "face-whoa.png";
	public static final String faceWink = "face-wink.png";
	
	protected String iconTheme;
	
	public IconFetch(String theTheme) {
		iconTheme = theTheme;
	}
	
	protected Image getImage(String thePath) {
		URL theURL = getClass().getResource(thePath);
		return new ImageIcon(theURL).getImage();
	}
	
	public Image loadImage(String theImage) {
		
		if (theImage.equals(Status.available)) {
			return getImage(iconTheme + "ava.png");
		} else if (theImage.equals(Status.away)) {
			return getImage(iconTheme + "away.png");
		} else if (theImage.equals(Status.doNotDistrub)) {
			return getImage(iconTheme + "dnd.png");
		} else if (theImage.equals(Status.offline)) {
			return getImage(iconTheme + "offline.png");
		} else if (theImage.equals(Status.superAvailable)) {
			return getImage(iconTheme + "chat.png");
		} else if (theImage.equals(Status.superAway)) {
			return getImage(iconTheme + "xa.png");
		} else if (theImage.equals(AccountSettings.AIMAccount)) {
			return getImage("aim.png");
		} else if (theImage.equals(AccountSettings.GoogleTalkAccount)) {
			return getImage("gtalk.png");
		} else if (theImage.equals(AccountSettings.MSNAccount)) {
			return getImage("msn.png");
		} else if (theImage.equals(AccountSettings.YahooAccount)) {
			return getImage("yahoo.png");
		}
		
		return null;
	}
	
	public String getURL(String thePath) {
		URL theURL = getClass().getResource(thePath);
		return theURL.toString();
	}
	
	public String getURL(String thePath, boolean parse) {
		if (!parse) { return getURL(thePath); }
		
		if (thePath.equals(Status.available)) {
			return getURL(iconTheme + "ava.png");
		} else if (thePath.equals(Status.away)) {
			return getURL(iconTheme + "away.png");
		} else if (thePath.equals(Status.doNotDistrub)) {
			return getURL(iconTheme + "dnd.png");
		} else if (thePath.equals(Status.offline)) {
			return getURL(iconTheme + "offline.png");
		} else if (thePath.equals(Status.superAvailable)) {
			return getURL(iconTheme + "chat.png");
		} else if (thePath.equals(Status.superAway)) {
			return getURL(iconTheme + "xa.png");
		} else if (thePath.equals(AccountSettings.AIMAccount)) {
			return getURL("aim.png");
		} else if (thePath.equals(AccountSettings.GoogleTalkAccount)) {
			return getURL("gtalk.png");
		} else if (thePath.equals(AccountSettings.MSNAccount)) {
			return getURL("msn.png");
		} else if (thePath.equals(AccountSettings.YahooAccount)) {
			return getURL("yahoo.png");
		}
		
		return getURL(thePath);
	}
}
