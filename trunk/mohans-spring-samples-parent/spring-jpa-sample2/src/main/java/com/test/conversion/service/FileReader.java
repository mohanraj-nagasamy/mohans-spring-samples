package com.test.conversion.service;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.test.conversion.dao.ConversionDAO;
import com.test.conversion.enums.ConversionStageStatus;
import com.test.conversion.enums.ConversionType;
import com.test.conversion.model.AccessCodePointsConversionStage;
import com.test.conversion.utils.ConversionUtils;

@Named
public class FileReader {

	private static final Logger log = Logger.getLogger(FileReader.class);

	@Inject
	private ConversionDAO conversionDAO;

	@Value("${number-of-lines-to-skip-from-file-header}")
	private int numberOfLinesToSkip;

	@Value("${number-of-lines-to-process-from-file}")
	private int numberOfLinesToProcess;

	public void populateAccessCodeFileData(String fileName) {
		populateFileDataIntoDB(fileName, ConversionType.ACCESS_CODE);
	}

	public void populatePointsFileData(String fileName) {
		populateFileDataIntoDB(fileName, ConversionType.POINTS);
	}

	@Transactional
	public void populateFileDataIntoDB(String fileName, ConversionType ct) {

		log.info("numberOfLinesToSkip --> " + numberOfLinesToSkip);

		long count = 0;
		LineIterator it = null;
		List<AccessCodePointsConversionStage> csList = new ArrayList<AccessCodePointsConversionStage>();

		long numberOfLines = countLines(fileName);

		try {
			it = FileUtils.lineIterator(new File(fileName));
			while (it.hasNext()) {
				String line = it.nextLine();

				//This is to skip the file headers
				if (numberOfLinesToSkip == ++count) {
					continue;
				}

				AccessCodePointsConversionStage cs = ConversionUtils.createConversionStageEntity(line, ct);
				cs.setLoadStatus(ConversionStageStatus.NEW.name());

				log.info("ConversionStage -> " + cs);

				csList.add(cs);

				//Creates entities in DB once it reaches numberOfLinesToProcess 
				if ((count % numberOfLinesToProcess) == 0) {
					conversionDAO.createOrUpdateAccessCodePointsConversionStage(csList);
					csList.clear();
					if(count == 300) {
					    throw new RuntimeException("To check TX");
					}
				}
				if (count == numberOfLines && csList.size() != 0) {
					conversionDAO.createOrUpdateAccessCodePointsConversionStage(csList);
					csList.clear();
				}

				log.info(count + "). " + line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			LineIterator.closeQuietly(it);
		}
	}

	private long countLines(String fileName) {

		long count = 0;
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new java.io.FileReader(fileName));

			while (reader.readLine() != null) {
				//remove comment to fast forward
				//long actiallySkipped = reader.skip(6000);
				//log.info("actiallySkipped -> " + actiallySkipped);
			}
			count = reader.getLineNumber();

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// IGnore
				}
			}
		}

		log.info("countLines --> " + count);

		return count;
	}
}
