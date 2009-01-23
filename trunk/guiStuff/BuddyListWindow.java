package guiStuff;

import upperAbstractionLayer.AccountManager;

public class BuddyListWindow extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcStatus;
    private javax.swing.JList jlBuddies;
    // End of variables declaration

    private AccountManager theAM;
	private BuddyListModel theModel;
    
    /** Creates new form BuddyListWindow */
    public BuddyListWindow(AccountManager AM) {
        initComponents();
        this.setVisible(true);
        
        theAM = AM;
        theModel = new BuddyListModel(theAM);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlBuddies = new javax.swing.JList();
        jcStatus = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlBuddies.setModel(theModel);
        jScrollPane1.setViewportView(jlBuddies);

        jcStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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




}
