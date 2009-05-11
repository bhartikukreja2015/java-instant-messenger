package centralServerStuff;

public class PacketLoginResponse {
	private boolean worked;
	private String data;
	
	public PacketLoginResponse(boolean work) { worked = work; }
	
	public void setWorked(boolean b) { worked = b; }
	public void setData(String d) { data = d; }
	
	public boolean isWorked() { return worked; }
	public String getData() { return data; }
}
