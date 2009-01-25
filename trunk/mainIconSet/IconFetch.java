package mainIconSet;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;


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
	
	protected Image getImage(String thePath) {
		URL theURL = getClass().getResource(thePath);
		return new ImageIcon(theURL).getImage();
	}
	
	public Image loadImage(String theImage) {
		
		if (theImage == Buddy.available) {
			return getImage("ava.png");
		} else if (theImage == Buddy.away) {
			return getImage("away.png");
		} else if (theImage == Buddy.doNotDistrub) {
			return getImage("dnd.png");
		} else if (theImage == Buddy.offline) {
			return getImage("offline.png");
		} else if (theImage == Buddy.superAvailable) {
			return getImage("chat.png");
		} else if (theImage == Buddy.superAway) {
			return getImage("xa.png");
		} else if (theImage == AccountSettings.AIMAccount) {
			return getImage("aim.png");
		} else if (theImage == AccountSettings.GoogleTalkAccount) {
			return getImage("gtalk.png");
		} else if (theImage == AccountSettings.MSNAccount) {
			return getImage("msn.png");
		} else if (theImage == AccountSettings.YahooAccount) {
			return getImage("yahoo.png");
		}
		
		return null;
	}
	
	public String getURL(String thePath) {
		URL theURL = getClass().getResource(thePath);
		return theURL.getPath();
	}
}
