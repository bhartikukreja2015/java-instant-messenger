package pidginIcons;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import abstractionLayer.AccountSettings;
import abstractionLayer.Buddy;


public class IconFetch {
	
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
}
