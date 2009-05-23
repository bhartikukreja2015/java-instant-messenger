package guiStuff.dewStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DewRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	private final int height = 36;
	private final int widthPadding = 40;
	
	private final int graphicX = 5;
	private final int graphicY = 8;
	
	protected int theWidth;
	protected boolean isSelected;
	protected JComponent toFetch;
	protected Action toShow;
	
	public DewRenderer(boolean selected, JComponent from, Action a) {
		theWidth = from.getWidth();
		isSelected = selected;
		toFetch = from;
		toShow = a;
	}
	
	public void paint(Graphics g) {

		theWidth = toFetch.getWidth();


		if (toShow == null) { return; }

		if (isSelected) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(-1, -1, theWidth + 1, height + 1);
		}


		int fontHeight = g.getFontMetrics().getHeight();

		// 5 for padding, then the font height
		int yBaseline = fontHeight;
		int yStatusBaseline = yBaseline * 2;

		//int yBaseline = 20;


		// drawString renders the text by baseline, so we want to position the text in the middle
		// so we go half way down, then up half the font size.

		g.setColor(Color.BLACK);
		//System.out.println(toShow.getStatus());


		g.drawImage(toShow.getImage(), graphicX, graphicY, null);
	

		g.drawString(toShow.getName(), widthPadding, yBaseline);
	

		float newSize = Float.valueOf("" + (g.getFont().getSize() * (5.0/6.0)));
		g.setFont(g.getFont().deriveFont(newSize));

		g.drawString(toShow.getDesc(), widthPadding, yStatusBaseline);

	}

	public Dimension getPreferredSize() {
		return new Dimension(theWidth, height);
	}
}
