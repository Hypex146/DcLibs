package logger;


public enum DcLogLevel {
	MINIMAL,  // Only warns and errors
	STANDART,  // Warns, error and important information
	DEBUG;  // Redundant information for debug plugin

	public static DcLogLevel toEnum(String string) {
		switch (string) {
			case "MINIMAL":
				return DcLogLevel.MINIMAL;
			case "STANDART":
				return DcLogLevel.STANDART;
			case "DEBUG":
				return DcLogLevel.DEBUG;
		}
		return null;
	}
	
}
