package com.nbp.weblab.date;

import static org.fest.assertions.api.Assertions.*;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.GregorianChronology;
import org.junit.Test;

public class YodaTimeTest {
	
	@Test
	public void shouldGetAfterOneDay() {
		Chronology chrono = GregorianChronology.getInstance();
		LocalDate theDay = new LocalDate(1582, 10, 4, chrono);
		assertThat(theDay.toString("yyyy.MM.dd")).isEqualTo("1582.10.04");
		
		LocalDate nextDay = theDay.plusDays(1);
		assertThat(nextDay.toString("yyyy.MM.dd")).isEqualTo("1582.10.05");
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
		DateTime theTime = new DateTime(1988,5,7,23,0, seoul);
		assertThat(theTime.toString("yyyy.MM.dd HH:mm")).isEqualTo("1988.05.07 23:00");
		assertThat(seoul.isStandardOffset(theTime.getMillis())).isTrue();
		
		DateTime after1Hour = theTime.plusHours(1);
		assertThat(after1Hour.toString("yyyy.MM.dd HH:mm")).isEqualTo("1988.05.08 01:00");
		assertThat(seoul.isStandardOffset(after1Hour.getMillis())).isFalse();
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
		DateTime theTime = new DateTime(1961, 8, 9, 23, 59, seoul);
		assertThat(theTime.toString("yyyy.MM.dd HH:mm")).isEqualTo("1961.08.09 23:59");
		
		DateTime after1Minute = theTime.plusMinutes(1);
		assertThat(after1Minute.toString("yyyy.MM.dd HH:mm")).isEqualTo("1961.08.10 00:30");
	}

	@Test
	public void shouldGetAfterTwoSecond() {
		DateTimeZone utc = DateTimeZone.forID("UTC");
		DateTime theTime = new DateTime(2012, 6, 30, 23, 59, 59, utc);
		assertThat(theTime.toString("yyyy.MM.dd HH:mm:ss")).isEqualTo("2012.06.30 23:59:59");
		
		DateTime after2Seconds = theTime.plusSeconds(2);
		assertThat(after2Seconds.toString("yyyy.MM.dd HH:mm:ss")).isEqualTo("2012.07.01 00:00:01");
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenWrongTimeZoneId(){
		DateTimeZone.forID("Seoul/Asia");
	}
	
}
