package guiStuff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jimPreferences.PreferencePoint;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;


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
public class AccountSettingsWindow extends javax.swing.JFrame implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JList jList1;
	private JButton jbEdit;
	private JButton jbMinus;
	private JButton jbPlus;

	
	public AccountSettingsWindow() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jList1 = new JList();
				jList1.setModel(new AccountListModel(new PreferencePoint()));
				jList1.addListSelectionListener(this);
			}
			{
				jbPlus = new JButton();
				jbPlus.setText("+");
				jbPlus.addActionListener(this);
			}
			{
				jbMinus = new JButton();
				jbMinus.setText("-");
				jbMinus.addActionListener(this);
			}
			{
				jbEdit = new JButton();
				jbEdit.setText("Edit");
				jbEdit.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(jList1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.UNRELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jbPlus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jbMinus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jbEdit, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(24, 24));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(thisLayout.createParallelGroup()
				    .add(GroupLayout.LEADING, jList1, 0, 548, Short.MAX_VALUE)
				    .add(GroupLayout.LEADING, thisLayout.createSequentialGroup()
				        .add(jbPlus, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.UNRELATED)
				        .add(jbMinus, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.UNRELATED)
				        .add(jbEdit, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				        .add(263)))
				.addContainerGap());
			pack();
			this.setSize(578, 252);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() != jList1) { return; }
		jbMinus.setEnabled(jList1.getSelectedIndex() != -1);
		jbEdit.setEnabled(jList1.getSelectedIndex() != -1);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jbPlus) {
			// create a new ModifyAccountWindow
			// with a the next account ID
			PreferencePoint pp = new PreferencePoint();
			ModifyAccountWindow maw = new ModifyAccountWindow(pp.getNextAccountID());
			
		} else if (arg0.getSource() == jbMinus) {
			// we need to delete the account
			String accountAlias = (String) jList1.getModel().getElementAt(jList1.getSelectedIndex());
			PreferencePoint pp = new PreferencePoint();
			
			
		} else if (arg0.getSource() == jbEdit) {
			
		}
	}

}
