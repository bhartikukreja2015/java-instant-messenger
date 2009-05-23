package guiStuff.dewStuff;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class DewRendererCreator implements ListCellRenderer {

	public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
		return new DewRenderer(arg3, arg0, (Action) arg1);
	}

}
