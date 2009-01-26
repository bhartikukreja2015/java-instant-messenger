package mainIconSet;

import java.util.ArrayList;

import abstractionLayer.IM;

public class IMHTMLParser {
	
	public boolean boldNames = true;
	public boolean colorName = false;
	public boolean loadSmiles = true;
	
	public String getHTML(IM theIM, String theirName) {
		StringBuilder theSB = new StringBuilder();
		
		if (boldNames) {
			theSB.append("<strong>");
		}
		
		theSB.append(theirName);
		
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
