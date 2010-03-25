package com.test.logging;

import org.apache.log4j.Logger;

public class TestLogDemo {

	private static final String LOG_FORMAT = "%15s: %15s | %15s | %15s";
	private static final String LOGGER_STRING_FORMAT = "%-35s =%s ";
	private static Logger logger = Logger.getLogger(TestLogDemo.class.getPackage().getName());

	public static void main(String[] args) throws Exception {

		logger.info("TestLogDemo.main() :: Enter11");

		Address primaryAddress = new Address();

		logger.info(String.format(LOGGER_STRING_FORMAT, "primary Address", primaryAddress));
		logger.info(String.format(LOGGER_STRING_FORMAT, "secondary Address", primaryAddress));
		logger.info(String.format(LOGGER_STRING_FORMAT, "Address", primaryAddress));

		//logger.info("trackerInfo: esn | authCode | modelID");

		logger.info(repeatString("*", 70));

		logger.info(String.format(LOG_FORMAT, "trackerInfo", "esn", "authCode", "modelID"));
		logger.info(repeatString("-", 70));
		logger.info(String.format(LOG_FORMAT, "", "esn--111", "authCode-222", "modelID-333"));
		logger.info(String.format("%s", repeatString("*", 70)));

		logger.info("TestLogDemo.main() :: Exit");

		TestLogDemo testLogDemo = new TestLogDemo("asdf asdf asfd");
		testLogDemo.sayHello();
		testLogDemo.sayHello112233445566778899001122334455667788990011223344556677889900();
	}

	private void sayHello112233445566778899001122334455667788990011223344556677889900() {
		logger.debug("TestLogDemo.sayHello() :: Enter");
		System.out.println(theMessage);

		String s = null;
		try {
			s.equals("");
		} catch (Exception e) {
			logger.info(String.format(LOGGER_STRING_FORMAT,
					"sayHello112233445566778899001122334455667788990011223344556677889900", e.getMessage()));
			logger.debug(String.format(LOGGER_STRING_FORMAT, "debug", e.getMessage()));
			logger.error(String.format(LOGGER_STRING_FORMAT, "error", e));
		}

		logger.debug("TestLogDemo.sayHello() :: Exit");

	}

	private String theMessage;

	public TestLogDemo(String message) {
		logger.info("TestLogDemo.TestLogDemo()");

		theMessage = message;
	}

	public void sayHello() {
		logger.debug("TestLogDemo.sayHello() :: Enter");
		System.out.println(theMessage);

		String s = null;
		try {
			s.equals("");
		} catch (Exception e) {
			logger.info(String.format(LOGGER_STRING_FORMAT,
					"sayHello112233445566778899001122334455667788990011223344556677889900", e.getMessage()));
			logger.debug(String.format(LOGGER_STRING_FORMAT, "debug", e.getMessage()));
			logger.error(String.format(LOGGER_STRING_FORMAT, "error", e));
		}

		logger.debug("TestLogDemo.sayHello() :: Exit");
	}

	public static String repeatString(String what, int times) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < times; i++) {
			sb.append(what);
		}

		return sb.toString();
	}
}
