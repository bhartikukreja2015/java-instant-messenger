package httpStuff;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

public class PostData {
	HashMap<String, String> data;
	
	
	// TODO have not really researched it much
	// do we want to use a HashMap?
	
	public PostData() {
		data = new HashMap<String, String>();
	}
	
	public void addData(String name, String value) {
		data.put(name, value);
	}
	
	public String getData() {
		Iterator<String> theIt = data.keySet().iterator();
		String toReturn = "";
		
		while (theIt.hasNext()) {
			String theKey = ((String) theIt.next());
			try {
				toReturn += URLEncoder.encode(theKey, "UTF-8") + "=" + URLEncoder.encode((String) data.get(theKey), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return toReturn;
		
		
	}
}
