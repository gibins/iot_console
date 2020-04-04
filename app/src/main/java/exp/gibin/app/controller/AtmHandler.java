package exp.gibin.app.controller;

import java.io.IOException;

import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereHandler;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

@AtmosphereHandlerService(path = "/handler")
public class AtmHandler implements AtmosphereHandler {

	@Override
	public void onRequest(AtmosphereResource resource) throws IOException {

		String method = resource.getRequest().getMethod();
		if (method.equals("GET")) {
			// We are using HTTP long-polling with an invite timeout
			resource.suspend();

		} else if (method.equals("POST")) {
			String message = resource.getRequest().getReader().readLine();
			resource.getResponse().write(message);
		}

	}

	@Override
	public void onStateChange(AtmosphereResourceEvent event) throws IOException {

		AtmosphereResource resource = event.getResource();

		if (resource.isSuspended()) {
			switch (resource.transport()) {
			case JSONP:
			case LONG_POLLING:
				event.getResource().resume();
			case WEBSOCKET:
				break;
			case STREAMING:
				resource.getResponse().getWriter().flush();
				break;
			}

		}

	}

	@Override
	public void destroy() {

	}

}
