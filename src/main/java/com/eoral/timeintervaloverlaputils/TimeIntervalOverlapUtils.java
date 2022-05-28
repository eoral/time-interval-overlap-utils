package com.eoral.timeintervaloverlaputils;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public final class TimeIntervalOverlapUtils {
	
	private TimeIntervalOverlapUtils() {}
		
	public static Optional<TimeInterval> getOverlap(TimeInterval interval1, TimeInterval interval2) {
		Objects.requireNonNull(interval1, "Intervals cannot be null.");
		Objects.requireNonNull(interval2, "Intervals cannot be null.");
		if (interval2.getStart().isAfter(interval1.getEnd()) || interval1.getStart().isAfter(interval2.getEnd())) {
			return Optional.empty();
		} else {
			Instant maxStart = interval1.getStart().isAfter(interval2.getStart()) ? interval1.getStart() : interval2.getStart();
			Instant minEnd = interval1.getEnd().isBefore(interval2.getEnd()) ? interval1.getEnd() : interval2.getEnd();
			if (maxStart.equals(minEnd)) {
				return Optional.empty();
			} else {
				TimeInterval interval = new TimeInterval(maxStart, minEnd);
				return Optional.of(interval);
			}
		}
	}
}
