package exp.gibin.app.repository;

import exp.gibin.app.model.Device;
// for jpa : extends JpaRepository<Device, Long>

public interface DeviceRepo  {
	
	int addDevice(Device device);

}
