package exp.gibin.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exp.gibin.app.service.HomeService;

@RestController
public class MainController {
	
	@Autowired
	HomeService homeService;
	
	
	@RequestMapping("/")
	public String getHome()
	{
		
		/*
		 * List<AtmosphereResource> wsConnectedResource =
		 * AtmWebsoket.wsConnectedResource; homeService.updateLog();
		 * 
		 * // traverse all connection for(AtmosphereResource atm : wsConnectedResource)
		 * { try {
		 * 
		 * atm.getResponse().getWriter().append("i got you "+""+new Date());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
		//return "Welcome "+wsConnectedResource.size();
		return "success";
	}
	
	@RequestMapping("/bcast")
	public String bcastMessage()
	{
		String status = "success ";
		/*
		 * Broadcaster broadcaster =
		 * broadcasterFactory.lookup("qerpDefaultBroadcaster"); if(broadcaster != null)
		 * { broadcaster.broadcast("broadcasted messaeg"); status +=
		 * broadcaster.getAtmosphereResources().size(); } else { status =
		 * "Oops no boradcaster"; }
		 */
		
		return status;
	}

}
