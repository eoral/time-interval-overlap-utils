package com.eoral.timeintervaloverlaputils;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class TimeIntervalOverlapUtils {
		
	public static Optional<TimeInterval> getOverlap(TimeInterval interval1, TimeInterval interval2) {
		if (interval1 == null || interval2 == null) {
			throw new IllegalArgumentException("Intervals cannot be null.");
		}
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
	
	public static Duration getOverlapDuration(TimeInterval interval1, TimeInterval interval2) {
		Optional<TimeInterval> optionalOverlap = getOverlap(interval1, interval2);
		if (optionalOverlap.isPresent()) {
			TimeInterval overlap = optionalOverlap.get();
			return Duration.between(overlap.getStart(), overlap.getEnd());
		} else {
			return Duration.ZERO;
		}
	}
}
