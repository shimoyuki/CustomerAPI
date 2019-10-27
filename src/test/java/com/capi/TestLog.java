package com.capi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

public class TestLog {
	private static Logger logger = LoggerFactory.getLogger(TestLog.class);
	
	@Test
	public void test() {
		String error = "error";
		String warn = "warn";
		logger.debug("this is a debug message");
		logger.info("this is a info message");
		logger.warn("this is a {} message",warn);
		logger.error("this is a {} message",error);
	}

}
