package com.nbp.weblab.date;

import static org.fest.assertions.api.Assertions.*;

import org.junit.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;

public class Jsr310Test {
	@Test
	public void shouldGetAfterOneDay() {
		LocalDate theDay = IsoChronology.INSTANCE.date(1582, 10, 4);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		assertThat(theDay.toString(format)).isEqualTo("1582.10.04");
		
		LocalDate nextDay = theDay.plusDays(1);
		assertThat(nextDay.toString(format)).isEqualTo("1582.10.05");
	}
	
	@Test
	public void shouldGetAfterOneHour() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1988, 5, 7, 23, 0, 0, 0, seoul);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertThat(theTime.toString(format)).isEqualTo("1988.05.07 23:00");
		ZoneRules seoulRules = seoul.getRules();
		assertThat(seoulRules.isDaylightSavings(Instant.from(theTime))).isFalse();

		ZonedDateTime after1Hour = theTime.plusHours(1);
		assertThat(after1Hour.toString(format)).isEqualTo("1988.05.08 01:00");
		assertThat(seoulRules.isDaylightSavings(Instant.from(after1Hour))).isTrue();
	}
	
	@Test
	public void shouldGetAfterOneMinute() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1961, 8, 9, 23, 59, 59, 0, seoul);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertThat(theTime.toString(format)).isEqualTo("1961.08.09 23:59");
		
		ZonedDateTime after1Minute = theTime.plusMinutes(1);
		assertThat(after1Minute.toString(format)).isEqualTo("1961.08.10 00:30");
	}

	@Test
	public void shouldGetAfterTwoSecond() {
		ZoneId utc = ZoneId.of("UTC");
		ZonedDateTime theTime = ZonedDateTime.of(2012, 6, 30, 23, 59, 59, 0, utc);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		assertThat(theTime.toString(format)).isEqualTo("2012.06.30 23:59:59");
		
		ZonedDateTime after2Seconds = theTime.plusSeconds(2);
		assertThat(after2Seconds.toString(format)).isEqualTo("2012.07.01 00:00:01");
	}

	@Test(expected=ZoneRulesException.class)
	public void shouldThrowExceptionWhenWrongTimeZoneId(){
		ZoneId.of("Seoul/Asia");
	}
}
