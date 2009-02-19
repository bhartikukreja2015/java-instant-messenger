package guiStuff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import jimPreferences.PreferencePoint;

import abstractionLayer.AccountSettings;


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
public class ModifyAccountWindow extends javax.swing.JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlAccountName;
	private JTextField jlName;
	private JComboBox jcbType;
	private JLabel jlUsername;
	private JTextField jtUsername;
	private JButton jbSave;
	private JButton jbCancel;
	private JCheckBox jcbEnabled;
	private JTextField jtPassword;
	private JLabel jlPassword;

	
	protected int accountID;
	
	public ModifyAccountWindow(int id) {
		super();
		initGUI();
		accountID = id;
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jlAccountName = new JLabel();
				jlAccountName.setText("Account name:");
			}
			{
				jlName = new JTextField();
			}
			{
				ComboBoxModel jcbTypeModel = 
					new DefaultComboBoxModel(
							new String[] { AccountSettings.AIMAccount, AccountSettings.GoogleTalkAccount, AccountSettings.MSNAccount, AccountSettings.YahooAccount });
				jcbType = new JComboBox();
				jcbType.setModel(jcbTypeModel);
			}
			{
				jlUsername = new JLabel();
				jlUsername.setText("Username:");
			}
			{
				jtUsername = new JTextField();
			}
			{
				jlPassword = new JLabel();
				jlPassword.setText("Password:");
			}
			{
				jtPassword = new JTextField();
			}
			{
				jcbEnabled = new JCheckBox();
				jcbEnabled.setText("Enabled");
			}
			{
				jbSave = new JButton();
				jbSave.setText("Save");
				jbSave.addActionListener(this);
			}
			{
				jbCancel = new JButton();
				jbCancel.setText("Cancel");
				jbCancel.setSize(120, 22);
				jbCancel.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jlName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jlAccountName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jcbType, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jtUsername, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jlUsername, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jtPassword, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jlPassword, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jcbEnabled, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jbSave, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jbCancel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jlAccountName, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jlUsername, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jlPassword, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				        .addGap(7)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 0, Short.MAX_VALUE)
				                .addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
				                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
				                .addComponent(jbSave, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addPreferredGap(jbCancel, jlName, LayoutStyle.ComponentPlacement.INDENT)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(jlName, GroupLayout.Alignment.LEADING, 0, 244, Short.MAX_VALUE)
				                    .addComponent(jtUsername, GroupLayout.Alignment.LEADING, 0, 244, Short.MAX_VALUE)
				                    .addComponent(jtPassword, GroupLayout.Alignment.LEADING, 0, 244, Short.MAX_VALUE)))))
				    .addComponent(jcbType, GroupLayout.Alignment.LEADING, 0, 366, Short.MAX_VALUE)
				    .addComponent(jcbEnabled, GroupLayout.Alignment.LEADING, 0, 366, Short.MAX_VALUE))
				.addContainerGap());
			pack();
			this.setSize(400, 225);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		//private JLabel jlAccountName;
		//private JTextField jlName;
		//private JComboBox jcbType;
		//private JLabel jlUsername;
		//private JTextField jtUsername;
		//private JButton jbSave;
		//private JButton jbCancel;
		//private JCheckBox jcbEnabled;
		//private JTextField jtPassword;
		//private JLabel jlPassword;
		
		if (arg0.getSource() == jbSave) {
			PreferencePoint pp = new PreferencePoint();
			AccountSettings mySettings = new AccountSettings();
			
			mySettings.setID(accountID);
			mySettings.setAlias(jlName.getName());
			mySettings.setAccountType((String) jcbType.getModel().getElementAt(jcbType.getSelectedIndex()));
			mySettings.setEnabled(jcbEnabled.isSelected());
			mySettings.setUsername(jtUsername.getText());
			mySettings.setPassword(jtPassword.getText());
			
			pp.saveAccount(mySettings);
		} else if (arg0.getSource() != jbCancel) {
			return;
		}
		// close this window
		// TODO someone should look this up... is this even right?
		this.setVisible(false);
	}

}
