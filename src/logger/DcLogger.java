package logger;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DcLogger {
	
	/**
	 * <p>
	 * Logger from spigotAPI
	 * </p>
	 */
	private Logger logger_ = null;
	
	/**
	 * <p>
	 * Current loging level
	 * </p>
	 */
	private DcLogLevel log_level_ = null;
	
	
	/**
	 * <p>
	 * Constructor of the DcLogger class
	 * </p>
	 * @param logger - Logger from spigotAPI
	 * @param log_level - Logging level
	 */
	public DcLogger(Logger logger, DcLogLevel log_level) {
		logger_ = logger;
		log_level_ = log_level;
	}
	
	
	/**
	 * <p>
	 * Set logging level
	 * </p>
	 * @param new_log_level - New logging level
	 */
	public void setLogLevel(DcLogLevel new_log_level) {
		log_level_ = new_log_level;
	}
	
	
	/**
	 * <p>
	 * Get current logging level
	 * </p>
	 * @return Current logging level
	 */
	public DcLogLevel getLogLevel() {
		return log_level_;
	}
	
	
	/**
	 * <p>
	 * Make a log message
	 * </p>
	 * @param log_level - Nessasary logging level for success logging 
	 * @param msg_type - Type of log message
	 * @param message - Message to log
	 */
	public void log(DcLogLevel log_level, Level msg_type, String message) {
		if (log_level_.compareTo(log_level) < 0) { return; }
		logger_.log(msg_type, message);
	}

}
