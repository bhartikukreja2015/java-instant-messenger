package guiStuff;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

import abstractionLayer.Buddy;

import mainIconSet.IconFetch;

public class IMWindowJLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	
	private static final int graphicX = 5;
	private static final int graphicY = 5;
	protected static final int graphicTextSpace = 10;
	
	protected Buddy toRender;
	
	public IMWindowJLabel()  {
		
	}
	
	public void setBuddy(Buddy b) {
		toRender = b;
	}
	
	public void paintComponent(Graphics g) {
		int theHeight = this.getHeight();
		int theWidth = this.getWidth();
		
		IconFetch theFetch = new IconFetch();
		
		Image theImg = theFetch.loadImage(toRender.getStatus());
		
		int graphicHeight = theImg.getHeight(null);
		int graphicWidth = theImg.getWidth(null);
		
		
		
		double scalingFactor = theHeight - (IMWindowJLabel.graphicY * 2); // inital Y we have
		scalingFactor = graphicHeight / scalingFactor;
		
		int iSF = (int) scalingFactor;
		// scalingFactor is now orignal / render
		
		g.drawImage(theFetch.loadImage(toRender.getStatus()), graphicX, graphicY, graphicHeight * iSF, graphicWidth * iSF, null);
	}
	
}
