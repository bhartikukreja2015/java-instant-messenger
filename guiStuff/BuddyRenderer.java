package guiStuff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import mainIconSet.IconFetch;


import abstractionLayer.Buddy;

public class BuddyRenderer extends Component {

	private static final long serialVersionUID = 1L;
	
	private final int height = 36;
	private final int widthPadding = 40;
	
	private final int graphicX = 5;
	private final int graphicY = 8;
	
	
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
			g.setColor(Color.LIGHT_GRAY);
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
		
		
		// drawString renders the text by baseline, so we want to position the text in the middle
		// so we go half way down, then up half the font size.
		
		IconFetch IF = new IconFetch();
		
		g.setColor(Color.BLACK);
		//System.out.println(toShow.getStatus());
		
		if (toShow.getStatus() == Buddy.available) {
			g.drawImage(IF.loadImage(Buddy.available), graphicX, graphicY, null);
			//g.setColor(Color.GREEN);
		} else if (toShow.getStatus() == Buddy.away) {
			g.drawImage(IF.loadImage(Buddy.away), graphicX, graphicY, null);
			//g.setColor(Color.RED);
		} else if (toShow.getStatus() == Buddy.doNotDistrub) {
			g.drawImage(IF.loadImage(Buddy.doNotDistrub), graphicX, graphicY, null);
			//g.setColor(Color.RED);
		} else if (toShow.getStatus() == Buddy.offline) {
			g.drawImage(IF.loadImage(Buddy.offline), graphicX, graphicY, null);
			g.setColor(Color.GRAY);
		} else if (toShow.getStatus() == Buddy.superAvailable) {
			g.drawImage(IF.loadImage(Buddy.superAvailable), graphicX, graphicY, null);
			//g.setColor(Color.GREEN);
		} else if (toShow.getStatus() == Buddy.superAway) {
			g.drawImage(IF.loadImage(Buddy.superAway), graphicX, graphicY, null);
			//g.setColor(Color.RED);
		}
		
		
		if (toShow.getAlias() != null) { 
			g.drawString(toShow.getAlias(), widthPadding, yBaseline);
		} else {
			g.drawString(toShow.getScreename(), widthPadding, yBaseline);
		}
		
		float newSize = Float.valueOf("" + (g.getFont().getSize() * (5.0/6.0)));
		//float newSize = 8;
		g.setFont(g.getFont().deriveFont(newSize));
		
		if (toShow.getStatusMessage() != null) {
			g.drawString(toShow.getStatusMessage(), widthPadding, yStatusBaseline);
		} else if (toShow.getStatus() != null) {
			g.drawString(toShow.getStatus(), widthPadding, yStatusBaseline);
		} else if (!toShow.isOnline()) {
			g.drawString(Buddy.offline, widthPadding, yStatusBaseline);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theWidth, height);
	}
}