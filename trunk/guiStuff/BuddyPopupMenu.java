package guiStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class BuddyPopupMenu extends JPopupMenu implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final String openIM = "Start IM";
	public static final String getInfo = "Get info";
	public static final String setAlias = "Set alias";
	
	protected BuddyPopupEvents ev;
	protected int theIndex;
	
	public BuddyPopupMenu(BuddyPopupEvents theEvents, int i) {
		super();
		
		ev = theEvents;
		theIndex = i;
		
		JMenuItem myJMI = new JMenuItem(BuddyPopupMenu.openIM);
		myJMI.addActionListener(this);
		this.add(myJMI);
		
		myJMI = new JMenuItem(BuddyPopupMenu.getInfo);
		myJMI.addActionListener(this);
		this.add(myJMI);
		
		myJMI = new JMenuItem(BuddyPopupMenu.setAlias);
		myJMI.addActionListener(this);
		this.add(myJMI);
		
	}

	public void actionPerformed(ActionEvent arg0) {	
		if (arg0.getSource() instanceof JMenuItem) {
			JMenuItem myItem = (JMenuItem) arg0.getSource();
			if (myItem.getText().equals(BuddyPopupMenu.openIM)) {
				ev.startIM(theIndex);
				return;
			} else if (myItem.getText().equals(BuddyPopupMenu.getInfo)) {
				ev.getInfo(theIndex);
				return;
			} else if (myItem.getText().equals(BuddyPopupMenu.setAlias)) {
				ev.setAlias(theIndex);
				return;
			}
		}
	}
}
