package exp.gibin.app.controller;

import java.io.IOException;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

//"/chatteer/{room: [a-zA-Z][a-zA-Z_0-9]*}"
@ManagedService(path = "/chathome")
public class AtmServelets {

	@Ready
	public void onReady(final AtmosphereResource atmosphereResource) throws IOException {
			// First, tell Atmosphere to allow bi-directional communication by suspending.
		
		System.out.println("Got request onready "+atmosphereResource.uuid());
		String message = atmosphereResource.getRequest().getReader().readLine();
		String method = atmosphereResource.getRequest().getMethod();
		if (method.equals("GET")) {
			// We are using HTTP long-polling with an invite timeout
			atmosphereResource.suspend();
				
		} else if (method.equals("POST")) {
			
			atmosphereResource.getResponse().write(message);
		}
		
	}

	@Disconnect
	public void onDisconnect(AtmosphereResourceEvent event) {

	}
	
	@Message
	public String onMessage(String message) {
		
		/*
		 * This returned message will display in the message box
		 * 
		 */
		return message;
	}


}
