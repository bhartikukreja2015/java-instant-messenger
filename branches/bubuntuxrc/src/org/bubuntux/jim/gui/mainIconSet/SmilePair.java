package org.bubuntux.jim.gui.mainIconSet;

import java.util.ArrayList;

import org.bubuntux.jim.preferences.PreferencePoint;

public class SmilePair {
	public String code;
	public String imgName;

	public SmilePair() {
		code = "";
		imgName = "";
	}

	public SmilePair(String pCode, String pName) {
		code = "\\Q" + pCode + "\\E";
		imgName = pName;
	}

	public String applyPair(String theMessage) {
		IconFetch myIF = new IconFetch((new PreferencePoint()).getIconTheme());
		return theMessage.replaceAll(code, "<img src='" + myIF.getURL(imgName)
				+ "'>");
	}

	public static ArrayList<SmilePair> getAllPairs() {
		ArrayList<SmilePair> toReturn = new ArrayList<SmilePair>();

		// faceSmile
		toReturn.add(new SmilePair(":-)", IconFetch.faceSmile));
		toReturn.add(new SmilePair(":)", IconFetch.faceSmile));

		// faceSad
		toReturn.add(new SmilePair(":-(", IconFetch.faceSad));
		toReturn.add(new SmilePair(":(", IconFetch.faceSad));

		// faceAngel
		toReturn.add(new SmilePair("O:-)", IconFetch.faceAngel));
		toReturn.add(new SmilePair("O:)", IconFetch.faceAngel));

		// faceCoolGuy
		toReturn.add(new SmilePair("8-)", IconFetch.faceCoolGuy));
		toReturn.add(new SmilePair("8)", IconFetch.faceCoolGuy));

		// faceTongue
		toReturn.add(new SmilePair(":-P", IconFetch.faceTongue));
		toReturn.add(new SmilePair(":P", IconFetch.faceTongue));

		// faceWink
		toReturn.add(new SmilePair(";)", IconFetch.faceWink));
		toReturn.add(new SmilePair(";-)", IconFetch.faceWink));

		return toReturn;
	}
}
