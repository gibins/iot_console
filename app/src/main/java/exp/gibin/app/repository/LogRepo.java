package exp.gibin.app.repository;

import java.util.List;

import exp.gibin.app.model.Log;

public interface LogRepo {
	public void insertLog(Log log);
	public Log getLog(int logId);
	public List<Log> getAllLog();
	
}
