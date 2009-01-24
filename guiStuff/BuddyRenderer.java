package guiStuff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import abstractionLayer.Buddy;

public class BuddyRenderer extends Component {

	private static final long serialVersionUID = 1L;
	private final int height = 36;
	
	
	Buddy toShow; 
	int theWidth;
	boolean isSelected;
	
	public BuddyRenderer(Buddy theBuddy, int width, boolean isSel) {
		super();
		toShow = theBuddy;
		theWidth = width;
		isSelected = isSel;
	}
	
	public void paint(Graphics g) {
		if (isSelected) {
			g.setColor(SystemColor.textHighlight);
			g.fillRect(-1, -1, theWidth + 1, height + 1);
		}
		
		// for now, let's just print the name of the user in a certain color
		// green for av.
		// red for away
		// gray for offline
		
		int fontHeight = g.getFontMetrics().getHeight();
		
		// 5 for padding, then the font height
		int yBaseline = fontHeight;
		int yStatusBaseline = yBaseline * 2;
		
		//int yBaseline = 20;
		
		System.out.println("Showing at baseline: " + yBaseline);
		
		// drawString renders the text by baseline, so we want to position the text in the middle
		// so we go half way down, then up half the font size.
		
		if (toShow.getStatus() == Buddy.away || toShow.getStatus() == Buddy.doNotDistrub || toShow.getStatus() == Buddy.superAway) {
			g.setColor(new Color(255,0,0));
		} else if (toShow.getStatus() == Buddy.available || toShow.getStatus() == Buddy.superAvailable) {
			g.setColor(new Color(0,255,0));
		} else {
			g.setColor(new Color(200,200,200));
		}
		
		if (toShow.getAlias() != null) { 
			g.drawString(toShow.getAlias(), 10, yBaseline);
		} else {
			g.drawString(toShow.getScreename(), 10, yBaseline);
		}
		
		float newSize = Float.valueOf("" + (g.getFont().getSize() * (5.0/6.0)));
		//float newSize = 8;
		g.setFont(g.getFont().deriveFont(newSize));
		
		if (toShow.getStatusMessage() != null) {
			g.drawString(toShow.getStatusMessage(), 10, yStatusBaseline);
		} else if (toShow.getStatus() != null) {
			g.drawString(toShow.getStatus(), 10, yStatusBaseline);
		} else if (!toShow.isOnline()) {
			g.drawString(Buddy.offline, 10, yStatusBaseline);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theWidth, height);
	}
}
