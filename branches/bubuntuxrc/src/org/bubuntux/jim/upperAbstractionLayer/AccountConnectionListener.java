package org.bubuntux.jim.upperAbstractionLayer;

import org.bubuntux.jim.abstractionLayer.AbstractAccount;

public interface AccountConnectionListener {
	public void startingConnection(AbstractAccount aa);

	public void connectedWith(AbstractAccount aa);

	public void disconnectedWith(AbstractAccount aa);
}
