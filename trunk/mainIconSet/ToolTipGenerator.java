package mainIconSet;

import abstractionLayer.Buddy;

public class ToolTipGenerator {
	public String getTooltip(Buddy b) {
		StringBuilder myBuild = new StringBuilder();
		
		IconFetch myFetch = new IconFetch();
		
		myBuild.append("<html><table width='400'><tr><td width='10%' align='center' valign='middle'>");
		
		myBuild.append("<img src='");
		myBuild.append(myFetch.getURL(b.getStatus(), true));
		
		myBuild.append("'></td><td width='90%'><strong>Alias: </strong>");
		myBuild.append(b.getAlias());
		myBuild.append("<br><strong>Screename: </strong>");
		myBuild.append(b.getScreename());
		myBuild.append("<br><strong>Account type: </strong>");
		myBuild.append(b.getAccount().getAccountSettings().getAccountType());
		myBuild.append("<br><strong>Account alias: </strong>");
		myBuild.append(b.getAccount().getAccountSettings().getAlias());
		
		myBuild.append("</td></tr></table></html>");
		return myBuild.toString();
	}
}
