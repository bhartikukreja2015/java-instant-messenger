package guiStuff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import abstractionLayer.Buddy;

public class BuddyRenderer extends Component {

	private static final long serialVersionUID = 1L;

	Buddy toShow; 
	
	public BuddyRenderer(Buddy theBuddy) {
		super();
		toShow = theBuddy;
	}
	
	public void paint(Graphics g) {
		// for now, let's just print the name of the user in a certain color
		// green for av.
		// red for away
		// gray for offline
		
		int yBaseline = (g.getClipBounds().height / 2) - (g.getFont().getSize() / 2);
		
		// drawString renders the text by baseline, so we want to position the text in the middle
		// so we go half way down, then up half the font size.
		
		if (toShow.getStatus() == Buddy.away || toShow.getStatus() == Buddy.DoNotDistrub || toShow.getStatus() == Buddy.superAway) {
			g.setColor(new Color(255,0,0));
		} else if (toShow.getStatus() == Buddy.available || toShow.getStatus() == Buddy.superAvailable) {
			g.setColor(new Color(0,255,0));
		} else {
			g.setColor(new Color(200,200,200));
		}
		
		g.drawString(toShow.getScreename(), 10, yBaseline);
		
	}
}
