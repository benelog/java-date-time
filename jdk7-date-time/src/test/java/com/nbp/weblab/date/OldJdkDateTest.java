package com.nbp.weblab.date;

import static org.fest.assertions.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;


public class OldJdkDateTest {
	@Test
	public void shouldGetAfterOneDay() {
		TimeZone utc = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(utc);
		calendar.set(1582, Calendar.OCTOBER , 4);
		String format = "yyyy.MM.dd";
		String theDay = toString(calendar, format, utc);
		assertThat(theDay).isEqualTo("1582.10.04");

		calendar.add(Calendar.DATE, 1);
		String nextDay = toString(calendar, format, utc);
		assertThat(nextDay).isEqualTo("1582.10.15");
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		TimeZone seoul = TimeZone.getTimeZone("Asia/Seoul");
		Calendar calendar = Calendar.getInstance(seoul);
		calendar.set(1988, Calendar.MAY , 7, 23, 0);
		String format = "yyyy.MM.dd HH:mm";
		String theTime = toString(calendar, format, seoul);
		assertThat(seoul.inDaylightTime(calendar.getTime())).isFalse();
		assertThat(theTime).isEqualTo("1988.05.07 23:00");
		
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		String after1Hour = toString(calendar, format, seoul);
		assertThat(seoul.inDaylightTime(calendar.getTime())).isTrue();
		assertThat(after1Hour).isEqualTo("1988.05.08 01:00");
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		TimeZone seoul = TimeZone.getTimeZone("Asia/Seoul");
		Calendar calendar = Calendar.getInstance(seoul);
		calendar.set(1961, Calendar.AUGUST, 9, 23, 59);
		String format = "yyyy.MM.dd HH:mm";
		String theTime = toString(calendar, format, seoul);
		assertThat(theTime).isEqualTo("1961.08.09 23:59");
		
		calendar.add(Calendar.MINUTE, 1);
		String after1Minute = toString(calendar, format, seoul);
		assertThat(after1Minute).isEqualTo("1961.08.10 00:30");
	}

	@Test
	public void shouldGetAfterTwoSecond() {
		TimeZone utc = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(utc);
		calendar.set(2012, Calendar.JUNE, 30, 23, 59, 59);
		String theTime = toString(calendar, "yyyy.MM.dd HH:mm:ss", utc);
		assertThat(theTime).isEqualTo("2012.06.30 23:59:59");
		
		calendar.add(Calendar.SECOND, 2);
		String afterTwoSeconds = toString(calendar, "yyyy.MM.dd HH:mm:ss", utc);
		assertThat(afterTwoSeconds).isEqualTo("2012.07.01 00:00:01");
	}
	
	@Test
	public void shouldSetGmtWhenWrongTimeZoneId(){
		TimeZone zone = TimeZone.getTimeZone("Seoul/Asia");
		assertThat(zone.getID()).isEqualTo("GMT");
	}

	private String toString(Calendar calendar, String pattern, TimeZone zone) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(zone);
		return format.format(calendar.getTime());
	}

	@Test
	public void shouldGetDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, 12, 31);
		assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2000);
		assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Calendar.JANUARY);		
		assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(31);		
	}

	
	@Test
	@SuppressWarnings("deprecation")
	public void shouldGetDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.JANUARY, 1);
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		assertThat(dayOfWeek).isEqualTo(Calendar.WEDNESDAY);		
		assertThat(dayOfWeek).isEqualTo(4);
		assertThat(calendar.getTime().getDay()).isEqualTo(3);
	}
}
