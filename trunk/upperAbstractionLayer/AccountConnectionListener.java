package upperAbstractionLayer;

import abstractionLayer.AbstractAccount;

public interface AccountConnectionListener {
	public void startingConnection(AbstractAccount aa);
	public void connectedWith(AbstractAccount aa);
	public void disconnectedWith(AbstractAccount aa);
}
