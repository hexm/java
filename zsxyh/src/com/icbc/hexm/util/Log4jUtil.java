package com.icbc.hexm.util;

import org.apache.log4j.Logger;

public class Log4jUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Log4jUtil.getLogger("hexm").debug("log test");

	}
	
	private static Logger logger = Logger.getLogger("");
	
	public static Logger getLogger() {
		return logger;
	}
	
	public static Logger getLogger(String loggerName) {
		return Logger.getLogger(loggerName);
	}

}
