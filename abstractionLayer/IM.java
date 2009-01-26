package abstractionLayer;

import mainIconSet.IMHTMLParser;

public class IM {
	public String from;
	public String to;
	public String message;
	public boolean automatic;
	public AbstractAccount theAccount;
	
	public String toHTML(Buddy bFrom) {
		IMHTMLParser myParse = new IMHTMLParser();
		// default values are fine for us
		
		
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
