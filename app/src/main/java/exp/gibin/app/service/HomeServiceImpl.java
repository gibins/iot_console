package exp.gibin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import exp.gibin.app.repository.DeviceRepo;

@Component
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	DeviceRepo deviceRepo;

	@Override
	public void updateLog() {
		System.out.println("Working Service");
		deviceRepo.addDevice(null);
	}

}
