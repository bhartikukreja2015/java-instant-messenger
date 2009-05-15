package guiStuff.buddyListHelperWindows;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import abstractionLayer.Buddy;


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
public class BuddyInfoWindow extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jlUsername;
	private JLabel jlAliasLabel;
	private JLabel jlStatusLabel;
	private JLabel jlAccountType;
	private JLabel jlAccountTypeLabel;
	private JLabel jlAccount;
	private JLabel jlAccountLabel;
	private JSeparator jSeparator1;
	private JLabel jlStatus;
	private JLabel jlAlias;
	private JLabel jlScreenname;
	private JLabel jlScreenameLabel;

	
	public void setBuddy(Buddy b) {
		jlAccountType.setText(b.getAccount().getAccountSettings().getAccountType());
		jlAccount.setText(b.getAccount().getAccountSettings().getAlias());
		jlStatus.setText(b.getStatus().getStatus());
		
		if (b.getAlias() != null) {
			jlAlias.setText(b.getAlias());
		} else {
			jlAlias.setText("Not set");
		}
		
		jlScreenname.setText(b.getScreename());
	}
	
	public BuddyInfoWindow() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jlUsername = new JLabel();
				jlUsername.setText("Pretty Username");
				jlUsername.setSize(368, 34);
			}
			{
				jlScreenameLabel = new JLabel();
				jlScreenameLabel.setText("Screenname:");
			}
			{
				jlScreenname = new JLabel();
				jlScreenname.setText("Their sn");
			}
			{
				jlAliasLabel = new JLabel();
				jlAliasLabel.setText("Alias:");
			}
			{
				jlAlias = new JLabel();
				jlAlias.setText("Their alias");
			}
			{
				jlStatusLabel = new JLabel();
				jlStatusLabel.setText("Status:");
			}
			{
				jlStatus = new JLabel();
				jlStatus.setText("Their status");
			}
			{
				jSeparator1 = new JSeparator();
			}
			{
				jlAccountLabel = new JLabel();
				jlAccountLabel.setText("Account:");
			}
			{
				jlAccount = new JLabel();
				jlAccount.setText("Account on");
			}
			{
				jlAccountTypeLabel = new JLabel();
				jlAccountTypeLabel.setText("Account type:");
			}
			{
				jlAccountType = new JLabel();
				jlAccountType.setText("The account type");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(jlUsername, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.UNRELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jlScreenameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jlScreenname, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.RELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jlAliasLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jlAlias, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.RELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jlStatusLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jlStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.UNRELATED)
				.add(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.UNRELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jlAccountLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jlAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.RELATED)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jlAccountTypeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jlAccountType, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(thisLayout.createParallelGroup()
				    .add(GroupLayout.LEADING, jlUsername, 0, 368, Short.MAX_VALUE)
				    .add(thisLayout.createSequentialGroup()
				        .add(thisLayout.createParallelGroup()
				            .add(GroupLayout.LEADING, jlScreenameLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				            .add(GroupLayout.LEADING, jlAliasLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				            .add(GroupLayout.LEADING, jlStatusLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				            .add(GroupLayout.LEADING, jlAccountLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				            .add(GroupLayout.LEADING, jlAccountTypeLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
				        .add(34)
				        .add(thisLayout.createParallelGroup()
				            .add(GroupLayout.LEADING, jlScreenname, 0, 239, Short.MAX_VALUE)
				            .add(GroupLayout.LEADING, jlAlias, 0, 239, Short.MAX_VALUE)
				            .add(GroupLayout.LEADING, jlStatus, 0, 239, Short.MAX_VALUE)
				            .add(GroupLayout.LEADING, jlAccount, 0, 239, Short.MAX_VALUE)
				            .add(GroupLayout.LEADING, jlAccountType, 0, 239, Short.MAX_VALUE)))
				    .add(GroupLayout.LEADING, jSeparator1, 0, 368, Short.MAX_VALUE))
				.addContainerGap());
			pack();
			this.setSize(400, 204);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
