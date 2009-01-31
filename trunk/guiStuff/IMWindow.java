package guiStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.GroupLayout;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import abstractionLayer.Buddy;
import abstractionLayer.IM;


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
public class IMWindow extends javax.swing.JFrame implements ActionListener, FocusListener {
    
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private IMWindowJLabel jlUsername;
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
    	jlUsername.setBuddy(to);
    	if (to.getAlias() != null) {
    		jlUsername.setText(to.getAlias());
    		this.setTitle(to.getAlias());
    	} else {
    		jlUsername.setText(to.getScreename());
    		this.setTitle(to.getScreename());
    	}
    }
    public Buddy getTo() { return chattingWith; }
    
    public void showIM(IM theIM) {
    	if (theIM.from.equals(chattingWith.getScreename())) {
    		currentHTML.append(theIM.toHTML(chattingWith, false));
    	} else {
    		// the IM is from somebody else
    		// is it from us?
    		if (theIM.from.equals(theIM.theAccount.getAccountSettings().getUsername())) {
    			// yes, it is from us.
    			currentHTML.append(theIM.toHTML(theIM.theAccount.getAccountSettings().getMyself(), true));
    		} else {
    			// it is some random person who get directed to this IM window
    			// well, it could be a chat
    			currentHTML.append(theIM.toHTML(theIM.getFromBuddy(), false));
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
        jlUsername = new IMWindowJLabel();
        //jlUsername.setSize(jlUsername.getWidth() * 2, jlUsername.getHeight());

        jTextField2.setText("jTextField2");

        jtIM.setEditable(false);
        jScrollPane1.setViewportView(jtIM);

        jtEntry.setText("");
        jtEntry.addActionListener(this);

        jlUsername.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setVerticalGroup(layout.createSequentialGroup()
        	.addGap(7)
        	.addComponent(jlUsername, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        	.addComponent(jScrollPane1, 0, 176, Short.MAX_VALUE)
        	.addGap(18)
        	.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        	.addComponent(jtEntry, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addContainerGap());
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addGroup(layout.createParallelGroup()
        	    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 376, Short.MAX_VALUE)
        	    .addComponent(jSeparator1, GroupLayout.Alignment.LEADING, 0, 376, Short.MAX_VALUE)
        	    .addComponent(jtEntry, GroupLayout.Alignment.LEADING, 0, 376, Short.MAX_VALUE)
        	    .addComponent(jlUsername, GroupLayout.Alignment.LEADING, 0, 376, Short.MAX_VALUE))
        	.addContainerGap());

        pack();
        
        this.addFocusListener(this);
    }// </editor-fold>                        

	public void actionPerformed(ActionEvent arg0) {		
		if (arg0.getSource() == jtEntry) {
			if (jtEntry.getText().equals("")) { return; } // don't send an IM with nothing in it
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
