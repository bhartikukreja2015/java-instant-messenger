package abstractionLayer;

import mainIconSet.IMHTMLParser;

public class IM {
	public String from;
	public String to;
	public String message;
	public boolean automatic = false;
	public boolean offline = false;
	public AbstractAccount theAccount;
	
	public String toHTML(Buddy bFrom, boolean isMe) {
		IMHTMLParser myParse = new IMHTMLParser();
		
		myParse.colorName = true;
		
		if (isMe) {
			myParse.theColor = IMHTMLParser.myColor;
		} else {
			myParse.theColor = IMHTMLParser.theirColor;
		}
		
		if (bFrom.getAlias() != null) {
			return myParse.getHTML(this, bFrom.getAlias());
		} else {
			return myParse.getHTML(this, bFrom.getScreename());
		}
	}
	
	public Buddy getFromBuddy() {
		Buddy b = new Buddy();
		b.setScreename(from);
		return b;
	}
}
