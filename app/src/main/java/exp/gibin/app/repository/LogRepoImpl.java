package exp.gibin.app.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import exp.gibin.app.model.Log;

public class LogRepoImpl implements LogRepo{
	
	@Autowired
	 SessionFactory sessionFactory;

	@Override
	public void insertLog(Log log) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(log);
		transaction.commit();
	}

	@Override
	public Log getLog(int logId) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Log log = session.get(Log.class, logId);
		transaction.commit();
		return log;
		
	}

	@Override
	public List<Log> getAllLog() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Log> log = session.createQuery("from co_log").list();
		transaction.commit();
		
		return log;
	}

}
