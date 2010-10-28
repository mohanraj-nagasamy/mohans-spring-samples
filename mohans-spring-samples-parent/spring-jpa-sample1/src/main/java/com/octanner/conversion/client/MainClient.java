package com.octanner.conversion.client;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.octanner.conversion.service.AccessCodeConversion;
import com.octanner.conversion.service.FileReader;

public class MainClient {
	private static final Logger log = Logger.getLogger(MainClient.class);

	private static String fileName;
	private static String fileType;

	public static void main(String[] args) {

		Options options = readCommandLineArgs(args);

		if (StringUtils.isNotBlank(fileName) && StringUtils.isNotBlank(fileType) && isFileAvailable()
				&& isValidFileType()) {
			bootStrapping();
		} else {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("usage", options);
		}
	}

	@SuppressWarnings("static-access")
	private static Options readCommandLineArgs(String[] args) {
		Option inputFile = OptionBuilder.withArgName("fileName").hasArg().withDescription("use given input File name")
				.create("fileName");
		Option inputFileType = OptionBuilder.withArgName("fileType").hasArg().withDescription(
				"use given input File type (access/points)").create("fileType");

		Options options = new Options();
		options.addOption(inputFile);
		options.addOption(inputFileType);

		CommandLineParser parser = new GnuParser();
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("fileName")) {
				fileName = line.getOptionValue("fileName");
			}

			if (line.hasOption("fileType")) {
				fileType = line.getOptionValue("fileType");
			}

			log.info("fileName -> " + fileName);
			log.info("fileType -> " + fileType);

		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}

		return options;

	}

	private static boolean isValidFileType() {
		if (StringUtils.equalsIgnoreCase(fileType, "access") || StringUtils.equalsIgnoreCase(fileType, "points")) {
			return true;
		}

		return false;
	}

	private static boolean isFileAvailable() {
		File file = new File(fileName);

		if (file.exists()) {
			return true;
		} else {
			throw new RuntimeException("File " + file.getPath() + " does not exist.");
		}
	}

	private static void bootStrapping() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/applicationContext.xml");

		FileReader fileReader = appContext.getBean(FileReader.class);
		AccessCodeConversion acConversion = appContext.getBean(AccessCodeConversion.class);

		if (StringUtils.equalsIgnoreCase(fileType, "access")) {
			//fileReader.populateAccessCodeFileData(fileName);
		}

		if (StringUtils.equalsIgnoreCase(fileType, "points")) {
			fileReader.populatePointsFileData(fileName);
		}

		acConversion.doConversion();
	}
}
