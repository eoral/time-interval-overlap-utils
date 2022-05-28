package com.eoral.timeintervaloverlaputils;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class TimeIntervalTest {
	
	@Test(expected = NullPointerException.class)
	public void constructor1ShouldThrowExceptionWhenStartIsNull() {
		new TimeInterval(null, Instant.now());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructor1ShouldThrowExceptionWhenEndIsNull() {
		new TimeInterval(Instant.now(), null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor1ShouldThrowExceptionWhenStartIsAfterEnd() {
		Instant start = Instant.parse("2022-04-17T07:00:00Z");
		Instant end = start.minusNanos(1);
		new TimeInterval(start, end);
	}
	
	@Test(expected = NullPointerException.class)
	public void constructor2ShouldThrowExceptionWhenStartIsNull() {
		new TimeInterval(null, "2022-04-17T07:00:00Z");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructor2ShouldThrowExceptionWhenEndIsNull() {
		new TimeInterval("2022-04-17T07:00:00Z", null);
	}
	
	@Test(expected = DateTimeParseException.class)
	public void constructor2ShouldThrowExceptionWhenStartIsNotValid() {
		new TimeInterval("an invalid instant", "2022-04-17T07:00:00Z");
	}
	
	@Test(expected = DateTimeParseException.class)
	public void constructor2ShouldThrowExceptionWhenEndIsNotValid() {
		new TimeInterval("2022-04-17T07:00:00Z", "an invalid instant");
	}
}
