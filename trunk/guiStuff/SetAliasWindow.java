package guiStuff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import upperAbstractionLayer.AccountManager;
import upperAbstractionLayer.AliasChangeEvent;

import abstractionLayer.Buddy;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jimPreferences.PreferencePoint;


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
public class SetAliasWindow extends javax.swing.JFrame implements ActionListener, DocumentListener {
	private JLabel jLabel1;
	private JTextField jtAlias;
	private JButton jbSave;
	private JButton jbCancel;
	
	private Buddy myBuddy;
	
	private AliasChangeEvent theACE;

	
	public SetAliasWindow(Buddy settingFor, AccountManager theAM) {
		super();
		myBuddy = settingFor;
		theACE = theAM;
		this.setTitle("Set alias for " + settingFor.getScreename());
		initGUI();
		if (myBuddy.getAlias() != null) {
			jtAlias.setText(myBuddy.getAlias());
		}
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Alias:");
			}
			{
				jtAlias = new JTextField();
				jtAlias.getDocument().addDocumentListener(this);
			}
			{
				jbSave = new JButton();
				jbSave.setText("Save");
				jbSave.addActionListener(this);
			}
			{
				jbCancel = new JButton();
				jbCancel.setText("Canel");
				jbCancel.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jtAlias, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.UNRELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jbSave, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jbCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.add(7));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(jLabel1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.RELATED)
				.add(thisLayout.createParallelGroup()
				    .add(GroupLayout.LEADING, jtAlias, 0, 358, Short.MAX_VALUE)
				    .add(GroupLayout.LEADING, thisLayout.createSequentialGroup()
				        .add(0, 237, Short.MAX_VALUE)
				        .add(jbCancel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.UNRELATED)
				        .add(jbSave, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap());
			pack();
			this.setSize(436, 99);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jbSave) {
			myBuddy.setAlias(jtAlias.getText(), true);
			theACE.buddyAliasChanged(myBuddy);
			this.setVisible(false);
		} else if (arg0.getSource() == jbCancel) {
			this.setVisible(false);
		}
	}
	
	protected boolean checkRules() {
		String theText = jtAlias.getText();
		
		if (theText == null || theText.equals("")) {
			jbSave.setEnabled(false);
			return false;
		}
		
		jbSave.setEnabled(true);
		return true;
	}

	public void changedUpdate(DocumentEvent arg0) { checkRules(); }
	public void insertUpdate(DocumentEvent arg0) { checkRules(); }
	public void removeUpdate(DocumentEvent arg0) { checkRules(); }

}
