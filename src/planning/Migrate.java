package planning;

import initializations.PolicyPool;
import java.util.List;
import database.model.DataCenter;
import database.model.Server;
import database.model.VirtualMachine;

public class Migrate extends Action {
	public Migrate(Server sourceServer, Server destinationServer,
			VirtualMachine vm) {
		super(sourceServer, destinationServer, vm);
	}

	@Override
	public DataCenter Do(DataCenter dc) {
		Server destServer = this.getDestinationServer();
		Server sourceServer = this.getSourceServer();
		updateVirtualMachine(destServer,dc);
		updateDestinationServer(destServer,dc);
		updateSourceServer(sourceServer,dc);
		return dc;
	}

	@Override
	public DataCenter Undo(DataCenter dc) {
		Server destServer=
				this.getDestinationServer();
		Server sourceServer = 
				this.getSourceServer();
		updateVirtualMachine(sourceServer,dc);
		updateSourceServer(destServer,dc);
		updateDestinationServer(sourceServer,dc);
		return dc;
	}

	private void updateVirtualMachine(Server server, DataCenter dc) {
		this.getVM().setHost(server);
		for (VirtualMachine virtualMachine: dc.getVMPool()) {
			if (virtualMachine.getID() == this.getVM().getID()) {
				virtualMachine.setHost(server);
			}
		}
	}

	private void updateDestinationServer(Server server, DataCenter dc) {
		for (Server serv : dc.getServerPool()) {
			if (server.getID() == serv.getID()) {
				server.getRunningVMs().add(this.getVM());
			}
		}
	}

	private void updateSourceServer(Server server, DataCenter dc) {
		for (Server serv : dc.getServerPool()) {
			if (server.getID() == serv.getID()) {
				server.getRunningVMs().remove(this.getVM());
			}
		}
	}
}
