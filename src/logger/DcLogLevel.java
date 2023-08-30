package logger;


public enum DcLogLevel {
	
	/**
	 * <p>
	 * Only warns and errors
	 * </p>
	 */
	MINIMAL,
	
	/**
	 * <p>
	 * Warns, error and important information
	 * </p>
	 */
	STANDART,
	
	/**
	 * <p>
	 * Redundant information for debug plugin
	 * </p>
	 */
	DEBUG;

	
	/**
	 * <p>
	 * Translating a string into an enumeration, if possible
	 * </p>
	 * @param string - The string to be translated into an enumeration
	 * @return Enumeration or null
	 */
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
