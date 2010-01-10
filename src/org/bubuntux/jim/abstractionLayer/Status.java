package org.bubuntux.jim.abstractionLayer;

public class Status {
	public static final String available = "Available";
	public static final String superAvailable = "Chatty";
	public static final String away = "Away";
	public static final String superAway = "Really away";
	public static final String doNotDistrub = "Do not distrub";
	public static final String offline = "Offline";

	protected String statusType;
	protected String statusMessage;

	public Status(String status, String message) {
		statusType = status;
		statusMessage = message;
	}

	public Status(String status) {
		statusType = status;
		statusMessage = null;
	}

	public Status() {
	}

	public void setStatus(String status) {
		statusType = status;
	}

	public void setStatusMessage(String message) {
		statusMessage = message;
	}

	public String getStatus() {
		return statusType;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public boolean isOnline() {
		return !(statusType.equals(Status.offline));
	}
}
