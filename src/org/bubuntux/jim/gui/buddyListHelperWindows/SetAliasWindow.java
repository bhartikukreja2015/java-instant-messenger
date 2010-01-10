package org.bubuntux.jim.gui.buddyListHelperWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.bubuntux.jim.abstractionLayer.Buddy;
import org.bubuntux.jim.upperAbstractionLayer.AccountManager;
import org.bubuntux.jim.upperAbstractionLayer.AliasChangeEvent;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class SetAliasWindow extends javax.swing.JFrame implements
		ActionListener, DocumentListener {
	private static final long serialVersionUID = 1L;
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
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
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
				jbSave.setSize(104, 22);
				jbSave.addActionListener(this);
			}
			{
				jbCancel = new JButton();
				jbCancel.setText("Canel");
				jbCancel.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap().addGroup(
							thisLayout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(jtAlias,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(jLabel1,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(
							thisLayout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(jbSave,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 22,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(jbCancel,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE,
									34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													jtAlias,
													GroupLayout.Alignment.LEADING,
													0, 356, Short.MAX_VALUE)
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addGap(
																	0,
																	119,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	jbCancel,
																	GroupLayout.PREFERRED_SIZE,
																	104,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.UNRELATED,
																	1,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	jbSave,
																	0,
																	122,
																	Short.MAX_VALUE)))
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

	public void changedUpdate(DocumentEvent arg0) {
		checkRules();
	}

	public void insertUpdate(DocumentEvent arg0) {
		checkRules();
	}

	public void removeUpdate(DocumentEvent arg0) {
		checkRules();
	}

}
