package org.bubuntux.jim.gui.accountGUI;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.bubuntux.jim.abstractionLayer.AccountSettings;

public class AccountRendererCreator implements ListCellRenderer {

	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		return new AccountRenderer(arg0.getWidth(), 32, arg3,
				(AccountSettings) arg1);
	}

}
