package org.bubuntux.jim.gui.mainIconSet;

import java.util.ArrayList;

import org.bubuntux.jim.abstractionLayer.IM;

public class IMHTMLParser {

	public boolean boldNames = true;
	public boolean colorName = false;

	public String theColor;

	public static final String myColor = "blue";
	public static final String theirColor = "red";

	public boolean loadSmiles = true;

	public String getHTML(IM theIM, String theirName) {
		StringBuilder theSB = new StringBuilder();

		if (boldNames) {
			theSB.append("<strong>");
		}

		if (colorName) {
			theSB.append("<font color='" + theColor + "'>");
		}

		theSB.append(theirName);

		if (colorName) {
			theSB.append("</font>");
		}

		if (boldNames) {
			theSB.append("</strong>");
		}

		theSB.append(": ");

		String formattedMessage = theIM.message;

		ArrayList<SmilePair> thePairs = SmilePair.getAllPairs();

		for (SmilePair sp : thePairs) {
			formattedMessage = sp.applyPair(formattedMessage);
		}

		theSB.append(formattedMessage);
		theSB.append("<br>");

		return theSB.toString();
	}
}
