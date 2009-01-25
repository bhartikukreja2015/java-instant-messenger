package guiStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;

import abstractionLayer.Buddy;
import abstractionLayer.IM;

public class IMWindow extends javax.swing.JFrame implements ActionListener, FocusListener {
    
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jlUsername;
    private javax.swing.JTextField jtEntry;
    private javax.swing.JEditorPane jtIM;
    // End of variables declaration
    
    private Buddy chattingWith;
    
    private StringBuilder currentHTML;
    
    private IMWindowManager myIMWM;
	
	private static final long serialVersionUID = 1L;
	/** Creates new form IMWindow */
    public IMWindow(IMWindowManager theW) {
        initComponents();
        this.setVisible(true);
        currentHTML = new StringBuilder();
        myIMWM = theW;
    }
    
    public void setTo(Buddy to) { 
    	chattingWith = to;
    	
    	if (to.getAlias() != null) {
    		jlUsername.setText(to.getAlias());
    	} else {
    		jlUsername.setText(to.getScreename());
    	}
    }
    public Buddy getTo() { return chattingWith; }
    
    public void showIM(IM theIM) {
    	if (theIM.from.equals(chattingWith.getScreename())) {
    		currentHTML.append(theIM.toHTML(chattingWith));
    	} else {
    		// the IM is from somebody else
    		// is it from us?
    		if (theIM.from.equals(theIM.theAccount.getAccountSettings().getUsername())) {
    			// yes, it is from us.
    			currentHTML.append(theIM.toHTML(theIM.theAccount.getAccountSettings().getMyself()));
    		} else {
    			// it is some random person who get directed to this IM window
    			// well, it could be a chat
    			currentHTML.append(theIM.toHTML(theIM.getFromBuddy()));
    		}
    	}
    	jtIM.setText(currentHTML.toString() + "<br>");
    	
    	// scroll...
    	this.scrollPaneToBottom();
    }
    
    private void scrollPaneToBottom() {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				jScrollPane1.getVerticalScrollBar().setValue(

						jScrollPane1.getVerticalScrollBar().getMaximum());

			}

		});

	}
    
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtIM = new JEditorPane("text/html", "");
        jtEntry = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jlUsername = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        jtIM.setEditable(false);
        jScrollPane1.setViewportView(jtIM);

        jtEntry.setText("");
        jtEntry.addActionListener(this);

        jlUsername.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jtEntry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jlUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        
        this.addFocusListener(this);
    }// </editor-fold>                        

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jtEntry) {
			IM theIM = new IM();
			theIM.to = chattingWith.getScreename();
			theIM.from = chattingWith.getAccount().getAccountSettings().getUsername();
			theIM.message = jtEntry.getText();
			theIM.theAccount = chattingWith.getAccount();
			
			myIMWM.sendIM(theIM);
			
			jtEntry.setText("");
		}
		
	}

	public void focusGained(FocusEvent arg0) {
		jtEntry.requestFocusInWindow();
	}

	public void focusLost(FocusEvent arg0) { }
    
                     
    
}
