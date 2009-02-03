package guiStuff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import upperAbstractionLayer.AccountManager;
import abstractionLayer.BuddyList;


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
public class MergeSetupWindow extends javax.swing.JFrame implements ActionListener, ItemListener {
	
	protected BuddyListModel theModel;
	protected BuddyListModel allBuddies;
	protected BuddyList theList;
	
	protected AccountManager myAM;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList jList1;
	private JScrollPane jScrollPane1;
	private JButton jbMinus;
	private JButton jbPlus;
	private JComboBox jComboBox1;
	
	
	public MergeSetupWindow(int mergeID, AccountManager theAM) {
		super();
		theList = theAM.getBuddyList().getBuddyListOfMerge(mergeID);
		theModel = new BuddyListModel(theList);
		allBuddies = new BuddyListModel(theAM);
		allBuddies.setHideMerged(false);
		
		myAM = theAM;
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setTitle("Edit merge");
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jScrollPane1 = new JScrollPane();
				{
					jList1 = new JList();
					jScrollPane1.setViewportView(jList1);
					jList1.setModel(theModel);
					jList1.setPreferredSize(new java.awt.Dimension(340, 88));
				}
			}
			{
				jComboBox1 = new JComboBox();
				jComboBox1.setModel(allBuddies);
				jComboBox1.setRenderer(new BuddyRendererCreator(myAM, false));
				jComboBox1.setSize(264, 32);
				jComboBox1.addItemListener(this);
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
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(jScrollPane1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.RELATED, 0, Short.MAX_VALUE)
				.add(thisLayout.createParallelGroup(GroupLayout.BASELINE)
				    .add(GroupLayout.BASELINE, jComboBox1, 0, 41, Short.MAX_VALUE)
				    .add(GroupLayout.BASELINE, jbPlus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .add(GroupLayout.BASELINE, jbMinus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.add(thisLayout.createParallelGroup()
				    .add(GroupLayout.LEADING, thisLayout.createSequentialGroup()
				        .add(jbPlus, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.RELATED)
				        .add(jbMinus, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.UNRELATED)
				        .add(jComboBox1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
				    .add(GroupLayout.LEADING, jScrollPane1, 0, 503, Short.MAX_VALUE))
				.addContainerGap());
			pack();
			this.setSize(537, 221);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
