package guiStuff;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

import mainIconSet.IconFetch;
import abstractionLayer.Buddy;

public class IMWindowJLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	
	private static final int graphicX = 0;
	private static final int graphicY = 0;
	protected static final int graphicTextSpace = 10;
	
	protected Buddy toRender;
	
	public IMWindowJLabel()  {
		super();
	}
	
	public void setBuddy(Buddy b) {
		toRender = b;
	}
	
	public void paint(Graphics g) {
		
		int theHeight = this.getHeight();
		
		// uncomment if ever needed...
		//int theWidth = this.getWidth();
		
		IconFetch theFetch = new IconFetch();
		
		Image theImg = theFetch.loadImage(toRender.getStatus());
		
		int graphicHeight = theImg.getHeight(null);
		int graphicWidth = theImg.getWidth(null);
		
		
		
		double scalingFactor = theHeight - (IMWindowJLabel.graphicY * 2); // inital Y we have
		scalingFactor =  scalingFactor / graphicHeight;
		
		// scalingFactor is now orignal / render
		
		int scaledWidth = (int) ((int) graphicWidth * scalingFactor);
		int scaledHeight = (int) ((int) graphicHeight * scalingFactor);
		
		g.drawImage(theFetch.loadImage(toRender.getStatus()), graphicX, graphicY, scaledWidth, scaledHeight, 0, 0, graphicWidth, graphicHeight, null);
		
		int fontHeight = g.getFontMetrics().getHeight();
		g.drawString((toRender.getAlias() == null ? toRender.getScreename() : toRender.getAlias()), scaledWidth + graphicTextSpace, (theHeight / 2) + (fontHeight / 4));
	
	}
	
}
