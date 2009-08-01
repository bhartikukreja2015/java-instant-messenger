package guiStuff.buddyListStuff;

import guiStuff.accountGUIStuff.AccountSettingsWindow;
import guiStuff.buddyListHelperWindows.BuddyInfoWindow;
import guiStuff.buddyListHelperWindows.MergeSetupWindow;
import guiStuff.buddyListHelperWindows.SetAliasWindow;
import guiStuff.imWindowStuff.IMWindowManager;
import guiStuff.statusStuff.StatusListModel;
import guiStuff.statusStuff.StatusRendererCreator;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import jimPreferences.PreferencePoint;

import abstractionLayer.Buddy;
import abstractionLayer.Status;
import upperAbstractionLayer.AccountManager;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BuddyListWindow extends javax.swing.JFrame implements MouseListener, ActionListener, BuddyPopupEvents {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private JCheckBoxMenuItem jCheckBoxMenuItem2;
    private JCheckBoxMenuItem jCheckBoxMenuItem1;
    private JSeparator jSeparator2;
    private JMenuItem jMenuItem5;
    private JSeparator jSeparator1;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem2;
    private javax.swing.JComboBox jcStatus;
    private javax.swing.JList jlBuddies;
    // End of variables declaration

    private AccountManager theAM;
    private JMenuItem jMenuItem1;
    private JMenu jMenu3;
    private JMenu jMenu2;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
	private BuddyListModel theModel;
	private IMWindowManager theIwm;
	
	private BuddyPopupMenu theMenu;
	
	private AccountSettingsWindow theASW;
	
    
    /** Creates new form BuddyListWindow */
    public BuddyListWindow(AccountManager AM, IMWindowManager iwm) {
    	theAM = AM;
        theIwm = iwm;
        theModel = new BuddyListModel(theAM);
        this.setTitle("Buddy List");
        initComponents();
        theASW = new AccountSettingsWindow();
    }
    
    public BuddyListModel getModel() { return theModel; }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlBuddies = new javax.swing.JList();
        jcStatus = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlBuddies.setModel(theModel);
        jlBuddies.addMouseListener(this);
        jlBuddies.setCellRenderer(new BuddyRendererCreator(theAM, true, true));
        jScrollPane1.setViewportView(jlBuddies);

        jcStatus.setModel(new StatusListModel());
        jcStatus.setRenderer(new StatusRendererCreator());
        jcStatus.addActionListener(this);
        jcStatus.setSelectedIndex(1);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        this.setMinimumSize(new java.awt.Dimension(205, 200));
        {
        	jMenuBar1 = new JMenuBar();
        	setJMenuBar(jMenuBar1);
        	{
        		jMenu1 = new JMenu();
        		jMenuBar1.add(jMenu1);
        		jMenu1.setText("File");
        		{
        			jMenuItem4 = new JMenuItem();
        			jMenu1.add(jMenuItem4);
        			jMenuItem4.setText("Connect");
        			jMenuItem4.addActionListener(this);
        		}
        		{
        			jMenuItem5 = new JMenuItem();
        			jMenu1.add(jMenuItem5);
        			jMenuItem5.setText("Disconnect");
        			jMenuItem5.addActionListener(this);
        		}
        		{
        			jSeparator1 = new JSeparator();
        			jMenu1.add(jSeparator1);
        		}
        		{
        			jMenuItem1 = new JMenuItem();
        			jMenu1.add(jMenuItem1);
        			jMenuItem1.setText("Quit");
        			jMenuItem1.addActionListener(this);
        		}
        	}
        	{
        		jMenu2 = new JMenu();
        		jMenuBar1.add(jMenu2);
        		jMenu2.setText("Buddy");
        		{
        			jMenuItem2 = new JMenuItem();
        			jMenu2.add(jMenuItem2);
        			jMenuItem2.setText("Create new merge");
        			jMenuItem2.addActionListener(this);
        		}
        		{
        			jSeparator2 = new JSeparator();
        			jMenu2.add(jSeparator2);
        		}
        		{
        			jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
        			jMenu2.add(jCheckBoxMenuItem1);
        			jCheckBoxMenuItem1.setText("Show offline buddies");
        			jCheckBoxMenuItem1.addActionListener(this);
        		}
        		{
        			jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
        			
        			jMenu2.add(jCheckBoxMenuItem2);
        			jCheckBoxMenuItem2.setText("Merge buddies");
        			jCheckBoxMenuItem2.addActionListener(this);
        		}
        	}
        	{
        		jMenu3 = new JMenu();
        		jMenuBar1.add(jMenu3);
        		jMenu3.setText("Settings");
        		{
        			jMenuItem3 = new JMenuItem();
        			jMenu3.add(jMenuItem3);
        			jMenuItem3.setText("Account settings");
        			jMenuItem3.addActionListener(this);
        		}
        	}
        }
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcStatus, 0, 205, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.isPopupTrigger() || arg0.isControlDown()) { // JDK bug! isPopupTrigger is NEVER TRUE on Ubuntu...
			
			// see if we clicked a real thing
			Rectangle theRect = jlBuddies.getCellBounds(jlBuddies.locationToIndex(arg0.getPoint()), jlBuddies.locationToIndex(arg0.getPoint()));
			if (!(theRect.contains(arg0.getPoint()))) { return; }
			
			theMenu = new BuddyPopupMenu(this, jlBuddies.locationToIndex(arg0.getPoint()));
			theMenu.show(jlBuddies, arg0.getX(), arg0.getY());
			return;
		}
		
		if (arg0.getClickCount() == 2) {
			int index = jlBuddies.locationToIndex(arg0.getPoint());
			theIwm.createIMWindow((Buddy) theModel.getElementAt(index));
		}
	}

	public void mouseEntered(MouseEvent arg0) { }

	public void mouseExited(MouseEvent arg0) { }

	public void mousePressed(MouseEvent arg0) { }

	public void mouseReleased(MouseEvent arg0) {
		// according to the java docs
		// we need to check in mouseClicked and
		// here in order to work right on all platforms.
		
		if (arg0.isPopupTrigger() || arg0.isControlDown()) { // JDK bug! isPopupTrigger is NEVER TRUE on Ubuntu...
			// see if we clicked a real thing
			Rectangle theRect = jlBuddies.getCellBounds(jlBuddies.locationToIndex(arg0.getPoint()), jlBuddies.locationToIndex(arg0.getPoint()));
			if (!(theRect.contains(arg0.getPoint()))) { return; }
			
			theMenu = new BuddyPopupMenu(this, jlBuddies.locationToIndex(arg0.getPoint()));
			theMenu.show(jlBuddies, arg0.getX(), arg0.getY());
			return;
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jcStatus) {
			// the user is changing their status.
			// let theAM know.
			
			Status s = new Status((String) jcStatus.getSelectedItem());
			
			theAM.setStatus(s);
			return;
		} else if (arg0.getSource() == jMenuItem1) {
			// quit
			
			// TODO is there a cleaner way to do this?
			System.exit(0);	
			return;
		} else if (arg0.getSource() == jMenuItem2) {
			// new merge
			MergeSetupWindow myMSW = new MergeSetupWindow(theAM.getBuddyList().getNextUnusedMergeID(), theAM);
			myMSW.setVisible(true);
			return;
		} else if (arg0.getSource() == jMenuItem3) {
			// account window
			theASW.setVisible(true);
		} else if (arg0.getSource() == jMenuItem4) {
			// connect
			theAM.loadEnabledAccounts(new PreferencePoint());
			return;
		} else if (arg0.getSource() == jMenuItem5) {
			// disconenct
			theAM.disconnectAll();
			return;
		} else if (arg0.getSource() == jCheckBoxMenuItem1) {
			// show offline
			theModel.setShowOffline(jCheckBoxMenuItem1.isSelected());
			return;
		} else if (arg0.getSource() == jCheckBoxMenuItem2) {
			// showing merge
			theModel.setHideMerged(jCheckBoxMenuItem2.isSelected());
			return;
		}
		
	}

	public void getInfo(int index) {
		BuddyInfoWindow myWin = new BuddyInfoWindow();
		myWin.setBuddy((Buddy) theModel.getElementAt(index));
		myWin.setVisible(true);
	}

	public void setAlias(int index) {
		SetAliasWindow mySet = new SetAliasWindow((Buddy) theModel.getElementAt(index), theAM);
		mySet.setVisible(true);
	}

	public void startIM(int index) {
		theIwm.createIMWindow((Buddy) theModel.getElementAt(index));
	}

	public void setupMerge(int index) {
		Buddy b = (Buddy) theModel.getElementAt(index);
		MergeSetupWindow myMSW;
		
		if (b.getMergeID() == 0) {
			myMSW = new MergeSetupWindow(theAM.getBuddyList().getNextUnusedMergeID(), theAM);
		} else {
			myMSW = new MergeSetupWindow(b.getMergeID(), theAM);
		}
		
		myMSW.setVisible(true);
	}




}
