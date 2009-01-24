package abstractionLayer;

public class IM {
	public String from;
	public String to;
	public String message;
	public boolean automatic;
	public AbstractAccount theAccount;
	
	public String toHTML(Buddy bFrom) {
		StringBuilder theSB = new StringBuilder();
		
		theSB.append("<strong>");
		if (bFrom.getAlias() != null) {
			theSB.append(bFrom.getAlias());
		} else {
			theSB.append(bFrom.getScreename());
		}
		
		theSB.append("</strong>: ");
		theSB.append(message);
		
		theSB.append("<br>");
		
		return theSB.toString();
	}
	
	public Buddy getFromBuddy() {
		Buddy b = new Buddy();
		b.setScreename(from);
		return b;
	}
}
