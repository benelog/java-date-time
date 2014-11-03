package com.nbp.weblab.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneChangePoint {
	public static void main(String[] args){
		printTime(1954,  3, 20, 22, 59);
		printTime(1954,  3, 20, 23,  0);
		printTime(1961,  8,  9, 23, 59);
		printTime(1961,  8, 10,  0,  0);
		printTime(1968,  9, 30, 23, 59);
		printTime(1968, 10,  1,  0,  0);
	}

	private static void printTime(int year, int month, int day, int hour, int second) {
		TimeZone zone = TimeZone.getTimeZone("Asia/Seoul");
		Calendar calendar = Calendar.getInstance(zone);
		calendar.set(year, month - 1, day, hour, second);

		String pattern = "yyyy.MM.dd HH:mm (Z)";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(zone);
		
		System.out.println(format.format(calendar.getTime()));
	}
}
