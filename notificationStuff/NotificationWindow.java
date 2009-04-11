package notificationStuff;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


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
public class NotificationWindow extends javax.swing.JFrame implements TimedEventCallback {
	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JLabel jlSubject;
	private JLabel jlMessage;
	private Canvas canvas1;
	
	
	protected TimedEventThread myThread;
	
	public NotificationWindow(Image theImage, String subject, String message, int timeToLive) {
		super();
		initGUI(theImage);
		// image x y width height bgcolor null
		jlSubject.setText(subject);
		jlMessage.setText(message);
		
		myThread = new TimedEventThread(timeToLive, this);
		myThread.start();
		
		this.setVisible(true);
		
	}
	
	
	private void initGUI(Image toShow) {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(337, 67));
				{
					canvas1 = new ImageDrawCanvas(toShow);
				}
				{
					jlSubject = new JLabel();
					jlSubject.setText("Subject");
				}
				{
					jlMessage = new JLabel();
					jlMessage.setText("Message");
				}
				jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(canvas1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addComponent(jlSubject, GroupLayout.Alignment.LEADING, 0, 245, Short.MAX_VALUE)
					    .addComponent(jlMessage, GroupLayout.Alignment.LEADING, 0, 245, Short.MAX_VALUE))
					.addContainerGap());
				jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jlSubject, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 16, Short.MAX_VALUE)
					        .addComponent(jlMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 12, GroupLayout.PREFERRED_SIZE))
					    .addComponent(canvas1, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void timeUp() {
		this.setVisible(false);
	}

}
