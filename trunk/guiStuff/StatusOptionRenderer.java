package guiStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import jimPreferences.PreferencePoint;

import mainIconSet.IconFetch;

import org.jivesoftware.smack.AccountManager;

public class StatusOptionRenderer extends JComponent {

		private static final long serialVersionUID = 1L;
		
		private final int height = 36;
		private final int widthPadding = 40;
		
		private final int graphicX = 5;
		private final int graphicY = 8;
		
		
		protected String toShow; 
		protected int theWidth;
		protected AccountManager myAM;
		protected JComponent toFetch;
		
		protected boolean showingMerge;
		
		public StatusOptionRenderer(String status, JComponent theComp) {
			super();
			
			toShow = status;
			toFetch = theComp;
			
		}
		
		public void paint(Graphics g) {
			
			theWidth = toFetch.getWidth();
			
			//System.out.println(theWidth);
			
			//System.out.println(toShow.getScreename() + "|" + toShow.getAlias());
			
			if (toShow == null) { return; }
			
			int yBaseline = 24;
			
			IconFetch IF = new IconFetch((new PreferencePoint()).getIconTheme());
			
			g.setColor(Color.BLACK);
			
			g.drawImage(IF.loadImage(toShow), graphicX, graphicY, null);
			g.drawString(toShow, widthPadding, yBaseline);
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(theWidth, height);
		}

}
