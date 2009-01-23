package guiStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import abstractionLayer.AccountSettings;

import jimPreferences.PreferencePoint;

public class AccountSettingsWindow extends JFrame implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JList accountList;
	private javax.swing.JButton jbSave;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JButton jbMinus;
	private javax.swing.JButton jbPlus;
	private javax.swing.JLabel jlAccountType;
	private javax.swing.JLabel jlPassword;
	private javax.swing.JLabel jlUsername;
	private javax.swing.JTextField jtPassword;
	private javax.swing.JTextField jtUsername;
	// End of variables declaration
	
	private AccountListModel theModel;



	public AccountSettingsWindow() {
		initComponents();
		this.setVisible(true);
	}

	private void initComponents() {

		jSplitPane1 = new javax.swing.JSplitPane();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		accountList = new javax.swing.JList();
		jbPlus = new javax.swing.JButton();
		jbMinus = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jlAccountType = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jlUsername = new javax.swing.JLabel();
		jtUsername = new javax.swing.JTextField();
		jlPassword = new javax.swing.JLabel();
		jtPassword = new javax.swing.JTextField();
		jbSave = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jSplitPane1.setDividerLocation(200);

		
		theModel = new AccountListModel(new PreferencePoint());
		accountList.setModel(theModel);
		accountList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		accountList.addListSelectionListener(this);
		
		jScrollPane1.setViewportView(accountList);

		jbPlus.setText("+");
		jbPlus.addActionListener(this);

		jbMinus.setText("-");
		jbMinus.addActionListener(this);
		jbMinus.setEnabled(false);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
										.addComponent(jbMinus)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jbPlus)))
										.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jbPlus)
								.addComponent(jbMinus))
								.addContainerGap())
		);

		jSplitPane1.setLeftComponent(jPanel1);

		jlAccountType.setText("Account type:");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AOL Instant Messanger", "Google Talk", "Yahoo Instant Messanger", "MSN" }));

		jlUsername.setText("Username:");

		jtUsername.setText("jTextField1");

		jlPassword.setText("Password:");

		jtPassword.setText("jTextField2");

		jbSave.setText("Save");
		jbSave.setEnabled(false);
		jbSave.addActionListener(this);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup()
										.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jlAccountType)
												.addComponent(jlUsername)
												.addComponent(jlPassword))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
														.addComponent(jComboBox1, 0, 310, Short.MAX_VALUE)
														.addComponent(jtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
														.addComponent(jbSave, javax.swing.GroupLayout.Alignment.TRAILING))
														.addContainerGap())
		);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jlAccountType)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jlUsername)
										.addComponent(jtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jlPassword)
												.addComponent(jtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
												.addComponent(jbSave)
												.addContainerGap())
		);

		jSplitPane1.setRightComponent(jPanel2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addContainerGap())
		);

		pack();
	}

	public void valueChanged(ListSelectionEvent arg0) {
		if (accountList.getSelectedIndex() >= theModel.getSize() || accountList.getSelectedValue() == null) {
			jbMinus.setEnabled(false);
			jbSave.setEnabled(false);
			
			jtUsername.setText("");
			jtPassword.setText("");
			
			jtUsername.setEnabled(false);
			jtPassword.setEnabled(false);
			jComboBox1.setEnabled(false);
			
			
			
			return;
		}
		
		jbMinus.setEnabled(true);
		jbSave.setEnabled(true);
		jtUsername.setEnabled(true);
		jtPassword.setEnabled(true);
		jComboBox1.setEnabled(true);
		
		AccountSettings mySettings = theModel.getSettings(accountList.getSelectedIndex());
		
		jtUsername.setText(mySettings.getUsername());
		jtPassword.setText(mySettings.getPassword());
		
		// "AOL Instant Messanger", "Google Talk", "Yahoo Instant Messanger", "MSN"
		if (mySettings.getAccountType() == AccountSettings.AIMAccount) {
			jComboBox1.setSelectedIndex(0);
		} else if (mySettings.getAccountType() == AccountSettings.GoogleTalkAccount) {
			jComboBox1.setSelectedIndex(1);
		} else if (mySettings.getAccountType() == AccountSettings.YahooAccount) {
			jComboBox1.setSelectedIndex(2);
		} else if (mySettings.getAccountType() == AccountSettings.MSNAccount) {
			jComboBox1.setSelectedIndex(3);
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jbSave) {
			// save the current account settings
			PreferencePoint pp = new PreferencePoint();
			int currentIndex = accountList.getSelectedIndex();
			
			AccountSettings toSave = theModel.getSettings(currentIndex);
			
			pp.deleteAccount(toSave.getID());
			
			toSave.setID(pp.getNextAccountID());
			toSave.setUsername(jtUsername.getText());
			toSave.setPassword(jtPassword.getText());
			
			//	"AOL Instant Messanger", "Google Talk", "Yahoo Instant Messanger", "MSN"
			
			if (jComboBox1.getSelectedIndex() == 0) {
				toSave.setAccountType(AccountSettings.AIMAccount);
			} else if (jComboBox1.getSelectedIndex() == 1) {
				toSave.setAccountType(AccountSettings.GoogleTalkAccount);
			} else if (jComboBox1.getSelectedIndex() == 2) {
				toSave.setAccountType(AccountSettings.YahooAccount);
			} else if (jComboBox1.getSelectedIndex() == 3) {
				toSave.setAccountType(AccountSettings.MSNAccount);
			}
			
			pp.saveAccount(toSave);
			theModel.update(pp);
			accountList.setSelectedIndex(theModel.getSize() - 1);
			
		} else if (arg0.getSource() == jbPlus) {
			PreferencePoint pp = new PreferencePoint();
			
			AccountSettings newAS = new AccountSettings();
			
			newAS.setID(pp.getNextAccountID());
			newAS.setAccountType(AccountSettings.AIMAccount);
			newAS.setUsername("New account");
			newAS.setPassword("");
			
			pp.saveAccount(newAS);

			theModel.update(pp);
			accountList.setSelectedIndex(theModel.getSize() - 1);
			this.valueChanged(null);
			
		} else if (arg0.getSource() == jbMinus) {
			PreferencePoint pp = new PreferencePoint();
			int currentIndex = accountList.getSelectedIndex();
			
			pp.deleteAccount(theModel.getSettings(currentIndex).getID());
			
			theModel.update(pp);
			
			this.valueChanged(null);
			
		}
		
	}



}
