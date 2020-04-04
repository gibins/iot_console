package exp.gibin.app.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import exp.gibin.app.model.Device;

@Repository
public class DeviceRepoImpl implements DeviceRepo {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int addDevice(Device device) {
		System.out.println("Working Repo");
		Device dev = new Device();
		dev.setDeviceName("Sample");
		
		Session session = sessionFactory.getCurrentSession();
		
		//session.beginTransaction();
		
		Transaction transaction = session.beginTransaction();
		session.save(dev);
		transaction.commit();
		System.out.println("Save Complete");
		
		return 0;
	}

}
