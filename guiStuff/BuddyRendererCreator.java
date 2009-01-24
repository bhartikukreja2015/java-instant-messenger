package guiStuff;

import java.awt.Canvas;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abstractionLayer.Buddy;

public class BuddyRendererCreator extends Canvas implements ListCellRenderer {
	
	
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		return new BuddyRenderer((Buddy) arg1);
	}
	

}
