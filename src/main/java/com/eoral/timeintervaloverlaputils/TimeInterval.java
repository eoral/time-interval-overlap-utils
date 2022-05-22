package com.eoral.timeintervaloverlaputils;

import java.time.Instant;
import java.util.Objects;

public class TimeInterval {
	
	private final Instant start;
	private final Instant end;
	
	/**
	 * @param start	 the start instant, inclusive, not null
	 * @param end  the end instant, exclusive, not null
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
	 * @param start	 the start instant as string ('2011-12-03T10:15:30Z'), inclusive, not null
	 * @param end  the end instant as string ('2011-12-03T10:15:30Z'), exclusive, not null
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
