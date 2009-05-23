package guiStuff.dewStuff;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
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
public class DewWindow extends javax.swing.JFrame {
	
	protected ActionManager theAM;
	protected DewModel theModel;
	protected DewRendererCreator theRC;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList jList1;
	private int width;
	
	
	public DewWindow(int w, ActionManager AM) {
		super();
		initGUI();
		width = w;
		
		theAM = AM;
		
		theModel = new DewModel(theAM);
		theRC = new DewRendererCreator();
		
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jList1 = new JList();
				getContentPane().add(jList1, BorderLayout.CENTER);
				
				jList1.setModel(theModel);
				jList1.setCellRenderer(theRC);
			}
			pack();
			setSize(width, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
