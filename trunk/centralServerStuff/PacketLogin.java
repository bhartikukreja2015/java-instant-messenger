package centralServerStuff;

import java.io.Serializable;

public class PacketLogin implements Serializable {
	private static final long serialVersionUID = -103222169256845203L;
	
	private String username;
	private String password;
	
	// 0 for create account
	// 1 for data requset
	// 2 for data send
	private int request;
	
	private String data;
	
	public PacketLogin(String name, String pass) {
		username = name;
		password = pass;
	}
	
	public void setUsername(String name) { username = name; }
	public void setPassword(String pass) { password = pass; }
	public void setRequest (int req) { request = req; }
	public void setData(String theData) { theData = data; }
	
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public int getRequest() { return request; }
	public String getData() { return data; }
	
}
