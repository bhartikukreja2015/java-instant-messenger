package org.bubuntux.jim.protocol.msn;

import java.util.ArrayList;

public class DoubleStopper {
	protected ArrayList<String> theBuddies;

	public DoubleStopper() {
		theBuddies = new ArrayList<String>();
	}

	public boolean checkBuddy(String username) {
		int i = 0;
		while (i != theBuddies.size()) {
			if (theBuddies.get(i).equals(username)) {
				return false;
			}
			i++;
		}

		theBuddies.add(username);

		return true;
	}

	public void reset() {
		theBuddies = new ArrayList<String>();
	}
}
