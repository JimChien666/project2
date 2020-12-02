package com.iii.eeit124.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**
	 * 時間轉換成字串
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 字串轉換成日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date format(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
