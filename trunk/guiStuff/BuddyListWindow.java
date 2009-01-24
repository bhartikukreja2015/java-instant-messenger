package guiStuff;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import abstractionLayer.Buddy;
import upperAbstractionLayer.AccountManager;

public class BuddyListWindow extends javax.swing.JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcStatus;
    private javax.swing.JList jlBuddies;
    // End of variables declaration

    private AccountManager theAM;
	private BuddyListModel theModel;
	private IMWindowManager theIwm;
    
    /** Creates new form BuddyListWindow */
    public BuddyListWindow(AccountManager AM, IMWindowManager iwm) {
        theAM = AM;
        theIwm = iwm;
        theModel = new BuddyListModel(theAM);
        this.setTitle("Buddy List");
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlBuddies = new javax.swing.JList();
        jcStatus = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlBuddies.setModel(theModel);
        jlBuddies.addMouseListener(this);
        jlBuddies.setCellRenderer(new BuddyRendererCreator());
        jScrollPane1.setViewportView(jlBuddies);

        jcStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { Buddy.available, Buddy.superAvailable, Buddy.away, Buddy.doNotDistrub, Buddy.superAway }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
		if (arg0.getClickCount() == 2) {
			int index = jlBuddies.locationToIndex(arg0.getPoint());
			theIwm.createIMWindow((Buddy) theModel.getElementAt(index));
		}
	}

	public void mouseEntered(MouseEvent arg0) { }

	public void mouseExited(MouseEvent arg0) { }

	public void mousePressed(MouseEvent arg0) { }

	public void mouseReleased(MouseEvent arg0) { }




}
