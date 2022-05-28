package com.eoral.timeintervaloverlaputils;

import java.time.Instant;
import java.util.Objects;

public class TimeInterval {
	
	private final Instant start;
	private final Instant end;
	
	/**
	 * @param start	not null and inclusive
	 * @param end	not null and exclusive
	 */
	public TimeInterval(Instant start, Instant end) {
		Objects.requireNonNull(start, "Start cannot be null.");
		Objects.requireNonNull(end, "End cannot be null.");
		if (start.isAfter(end)) {
			throw new IllegalArgumentException("Start and end must be ordered.");
		}
		this.start = start;
		this.end = end;
	}
	
	/**
	 * @param start	a string that can be parsed to java.time.Instant (such as '2011-12-03T10:15:30Z'), (not null and inclusive)
	 * @param end	a string that can be parsed to java.time.Instant (such as '2011-12-03T10:15:30Z'), (not null and exclusive)
	 */
	public TimeInterval(String start, String end) {
		this(Instant.parse(start), Instant.parse(end));
	}

	public Instant getStart() {
		return start;
	}

	public Instant getEnd() {
		return end;
	}
}
