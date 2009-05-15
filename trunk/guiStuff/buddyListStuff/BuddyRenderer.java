package guiStuff.buddyListStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;
import mainIconSet.ToolTipGenerator;
import upperAbstractionLayer.AccountManager;
import abstractionLayer.Buddy;
import abstractionLayer.Status;

public class BuddyRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private final int height = 36;
	private final int widthPadding = 40;
	
	private final int graphicX = 5;
	private final int graphicY = 8;
	
	
	protected Buddy toShow; 
	protected int theWidth;
	protected boolean isSelected;
	protected AccountManager myAM;
	protected JComponent toFetch;
	
	protected boolean showingMerge;
	
	public BuddyRenderer(Buddy theBuddy, JComponent theComp, boolean isSel, AccountManager theAM, boolean showMerge) {
		super();
		toShow = theBuddy;
	//	System.out.println("Renderer made for: " + toShow.getScreename());
		
		theWidth = theComp.getWidth();
		isSelected = isSel;
		myAM = theAM;
		toFetch = theComp;
		
		showingMerge = showMerge;
		
		ToolTipGenerator TTG = new ToolTipGenerator();
		this.setToolTipText(TTG.getTooltip(theBuddy));
	}
	
	public void paint(Graphics g) {
		
		//System.out.println("Painting: " + toShow.getScreename());
		
		theWidth = toFetch.getWidth();
		
		//System.out.println(theWidth);
		
		//System.out.println(toShow.getScreename() + "|" + toShow.getAlias());
		
		if (toShow == null) { return; }
		
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
		
		IconFetch IF = new IconFetch((new PreferencePoint()).getIconTheme());
		
		g.setColor(Color.BLACK);
		//System.out.println(toShow.getStatus());
		
		if (toShow.getStatus().getStatus() == Status.available) {
			g.drawImage(IF.loadImage(Status.available), graphicX, graphicY, null);
			//g.setColor(Color.GREEN);
		} else if (toShow.getStatus().getStatus() == Status.away) {
			g.drawImage(IF.loadImage(Status.away), graphicX, graphicY, null);
			//g.setColor(Color.RED);
		} else if (toShow.getStatus().getStatus() == Status.doNotDistrub) {
			g.drawImage(IF.loadImage(Status.doNotDistrub), graphicX, graphicY, null);
			//g.setColor(Color.RED);
		} else if (toShow.getStatus().getStatus() == Status.offline) {
			g.drawImage(IF.loadImage(Status.offline), graphicX, graphicY, null);
			g.setColor(Color.GRAY);
		} else if (toShow.getStatus().getStatus() == Status.superAvailable) {
			g.drawImage(IF.loadImage(Status.superAvailable), graphicX, graphicY, null);
			//g.setColor(Color.GREEN);
		} else if (toShow.getStatus().getStatus() == Status.superAway) {
			g.drawImage(IF.loadImage(Status.superAway), graphicX, graphicY, null);
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
		
		if (toShow.getStatus().getStatusMessage() != null) {
			g.drawString(toShow.getStatus().getStatusMessage(), widthPadding, yStatusBaseline);
		} else if (toShow.getStatus() != null) {
			g.drawString(toShow.getStatus().getStatus(), widthPadding, yStatusBaseline);
		} else if (!toShow.isOnline()) {
			g.drawString(Status.offline, widthPadding, yStatusBaseline);
		}
		
		
		
		// see if we are part of a merge
		if ((toShow.getMergeID() != 0) && showingMerge) {
			
			//System.out.println(toShow.getScreename() + " is in merge: " + toShow.getMergeID());
			
			// we're part of merge. Figure out how many are in the merge, and show the text
			int numInMerge = myAM.getBuddyList().getBuddiesInMerge(toShow.getMergeID()).size();
			numInMerge--;
			int xMergeText = theWidth - 50;
			
			g.drawString("+ " + numInMerge, xMergeText, yStatusBaseline);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theWidth, height);
	}
}
