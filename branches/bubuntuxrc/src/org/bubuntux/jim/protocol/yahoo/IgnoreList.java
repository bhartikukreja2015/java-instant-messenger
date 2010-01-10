package org.bubuntux.jim.protocol.yahoo;

import java.util.ArrayList;

public class IgnoreList {
	protected ArrayList<String> theList;

	public IgnoreList() {
		theList = new ArrayList<String>();
	}

	public void addUser(String username) {
		// System.out.println("Ignore: " + username);
		theList.add(username);
	}

	public boolean checkUser(String username) {
		int i = 0;

		while (i != theList.size()) {
			if (theList.get(i).equals(username)) {
				theList.remove(i);
				return true;
			}
			i++;
		}

		return false;
	}
}
