package com.iii.eeit124.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	/**
	 * 時間轉換成字串
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		return sdf.format(date);
	}

	/**
	 * 字串轉換成日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date format(String date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("format date failed.");
		}
	}

	public static void main(String[] args) {
		System.out.println("" + DateUtils.format(new Date()));
	}

}
