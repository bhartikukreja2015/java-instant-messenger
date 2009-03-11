package httpStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPPostSocket {
	public String sendPost(String url, PostData pd) {
		String toReturn = "";
		
		try {
			URL theURL = new URL(url);
			URLConnection theCon = theURL.openConnection();
			
			theCon.setDoOutput(true);
			
			// send the data
			OutputStreamWriter wr = new OutputStreamWriter(theCon.getOutputStream());
			wr.write(pd.getData());
			wr.flush();
			wr.close();
			
			// get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(theCon.getInputStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				toReturn += line;
			}
		} 
		catch (MalformedURLException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		
		
		return toReturn;
	}
}
