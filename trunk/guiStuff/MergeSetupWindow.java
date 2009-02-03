package guiStuff;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class MergeSetupWindow extends javax.swing.JFrame {
	private JList jList1;
	private JScrollPane jScrollPane1;
	private JButton jbMinus;
	private JButton jbPlus;
	private JComboBox jComboBox1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MergeSetupWindow inst = new MergeSetupWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MergeSetupWindow() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jScrollPane1 = new JScrollPane();
				{
					ListModel jList1Model = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jList1 = new JList();
					jScrollPane1.setViewportView(jList1);
					jList1.setModel(jList1Model);
					jList1.setPreferredSize(new java.awt.Dimension(170, 120));
				}
			}
			{
				ComboBoxModel jComboBox1Model = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				jComboBox1 = new JComboBox();
				jComboBox1.setModel(jComboBox1Model);
			}
			{
				jbPlus = new JButton();
				jbPlus.setText("+");
			}
			{
				jbMinus = new JButton();
				jbMinus.setText("-");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jComboBox1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jbPlus, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jbMinus, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(39, 39));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jbPlus, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addComponent(jbMinus, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(jComboBox1, 0, 272, Short.MAX_VALUE))
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 362, Short.MAX_VALUE))
				.addContainerGap());
			pack();
			this.setSize(392, 173);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
