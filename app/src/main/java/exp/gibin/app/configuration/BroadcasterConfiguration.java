package exp.gibin.app.configuration;

import org.atmosphere.cpr.Broadcaster;
import org.springframework.stereotype.Component;

@Component
public class BroadcasterConfiguration {
	
	Broadcaster broadcaster;
	
	final String DEFAULT_BROADCASTER = "BROADCASTER";

	public Broadcaster getBroadcaster() {
		broadcaster = AtmosphereConfiguration.broadcasterFactory.lookup(DEFAULT_BROADCASTER, true);
		return broadcaster;
	}

	public void setBroadcaster(Broadcaster broadcaster) {
		this.broadcaster = broadcaster;
	}
	
	

	
	
}
