package logger;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DcLogger {
	
	// objects
	private Logger logger_ = null;
	private DcLogLevel log_level_ = null;
	
	
	// DcLogger
	public DcLogger(Logger logger, DcLogLevel log_level) {
		logger_ = logger;
		log_level_ = log_level;
	}
	
	
	// void setLogLevel
	public void setLogLevel(DcLogLevel new_log_level) {
		log_level_ = new_log_level;
	}
	
	
	// DcLogLevel getLogLevel
	public DcLogLevel getLogLevel() {
		return log_level_;
	}
	
	
	// void log
	public void log(DcLogLevel log_level, Level msg_type, String message) {
		if (log_level_.compareTo(log_level) < 0) { return; }
		logger_.log(msg_type, message);
	}

}
