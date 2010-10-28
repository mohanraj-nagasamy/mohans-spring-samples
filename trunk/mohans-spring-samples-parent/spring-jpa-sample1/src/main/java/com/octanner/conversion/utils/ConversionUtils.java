package com.octanner.conversion.utils;

import java.math.BigDecimal;

import com.octanner.conversion.enums.ConversionType;
import com.octanner.conversion.enums.FileHeader;
import com.octanner.conversion.model.AccessCodePointsConversionStage;

public class ConversionUtils {
	public static AccessCodePointsConversionStage createConversionStageEntity(String line, ConversionType conversionType) {

		String[] lineItems = line.split(",");

		if (conversionType == ConversionType.ACCESS_CODE) {
			return createEntityFromAccessCodeLineItem(lineItems);
		} else if (conversionType == ConversionType.POINTS) {
			return createEntityFromPointsLineItem(lineItems);
		} else {
			throw new RuntimeException("Invalid ConversionType {" + conversionType + "}");
		}

	}

	private static AccessCodePointsConversionStage createEntityFromAccessCodeLineItem(String[] lineItems) {

		AccessCodePointsConversionStage cs = new AccessCodePointsConversionStage();

		cs.setAccessCode(lineItems[FileHeader.ACCESS_CODE.ACCESS_CODE_VALUE.getIndex()]);
		cs.setAwardAmount(new BigDecimal(lineItems[FileHeader.ACCESS_CODE.AWARD_AMOUNT.getIndex()]));
		cs.setEmployeeUniqueId(lineItems[FileHeader.ACCESS_CODE.EMPLOYEE_UNIQUE_ID.getIndex()]);

		return cs;
	}

	private static AccessCodePointsConversionStage createEntityFromPointsLineItem(String[] lineItems) {

		AccessCodePointsConversionStage cs = new AccessCodePointsConversionStage();

		cs.setPoints(new BigDecimal(lineItems[FileHeader.POINTS.POINTS_BALANCE.getIndex()]));
		cs.setEmployeeUniqueId(lineItems[FileHeader.POINTS.EMPLOYEE_UNIQUE_ID.getIndex()]);

		return cs;
	}

}
