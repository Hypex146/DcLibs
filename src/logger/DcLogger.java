package logger;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DcLogger {
	private Logger logger_ = null;
	private DcLogLevel log_level_ = null;
	
	public DcLogger(Logger logger, DcLogLevel log_level) {
		logger_ = logger;
		log_level_ = log_level;
	}
	
	public void setLogLevel(DcLogLevel new_log_level) {
		log_level_ = new_log_level;
	}
	
	public DcLogLevel getLogLevel() {
		return log_level_;
	}
	
	public void log(DcLogLevel log_level, Level msg_type, String message) {
		if (log_level_.compareTo(log_level) < 0) { return; }
		logger_.log(msg_type, message);
	}

}
