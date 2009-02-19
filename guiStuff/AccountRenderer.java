package guiStuff;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class AccountRenderer extends Component {
	
	private static final long serialVersionUID = 1L;
	protected int theWidth;
	protected int theHeight;
	
	public void paint(Graphics g, int width, int height) {
		theWidth = width;
		theHeight = height;
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theWidth, theHeight);
	}
}
