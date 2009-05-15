package guiStuff.accountGUIStuff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;

import abstractionLayer.AccountSettings;

public class AccountRenderer extends Component {
	
	private static final long serialVersionUID = 1L;
	protected int theWidth;
	protected int theHeight;
	
	protected boolean isSelected;
	
	protected AccountSettings toShow;
	
	public AccountRenderer(int width, int height, boolean isSel, AccountSettings toRender) {
		theWidth = width;
		theHeight = height;
		isSelected = isSel;
		toShow = toRender;
	}
	
	public void paint(Graphics g) {
		if (toShow == null) { return; }
		
		
		if (isSelected) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(-1, -1, theWidth + 1, theHeight + 1);
		}
		
		g.setColor(Color.BLACK);
		
		int fontHeight = g.getFontMetrics().getHeight();
		
		int fontBaseline = (theHeight / 2) + (fontHeight / 2);
		
		IconFetch myFetch = new IconFetch((new PreferencePoint()).getIconTheme());
		Image myImg = myFetch.loadImage(toShow.getAccountType());
		g.drawImage(myImg, 5, 5, null);
				
		g.drawString((toShow.getAlias() != null ? toShow.getAlias() : toShow.getUsername()), myImg.getWidth(null) + 10, fontBaseline);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theWidth, theHeight);
	}
}
