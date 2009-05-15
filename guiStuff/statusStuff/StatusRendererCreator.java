package guiStuff.statusStuff;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class StatusRendererCreator implements ListCellRenderer {

	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		return new StatusOptionRenderer((String) arg1, arg0);
	}

}
