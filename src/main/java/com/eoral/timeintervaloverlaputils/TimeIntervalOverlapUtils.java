package com.eoral.timeintervaloverlaputils;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class TimeIntervalOverlapUtils {
		
	public static Optional<Interval> getOverlap(Interval interval1, Interval interval2) {
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
				Interval interval = new Interval(maxStart, minEnd);
				return Optional.of(interval);
			}
		}
	}
	
	public static Duration getOverlapDuration(Interval interval1, Interval interval2) {
		Optional<Interval> optionalOverlap = getOverlap(interval1, interval2);
		if (optionalOverlap.isPresent()) {
			Interval overlap = optionalOverlap.get();
			return Duration.between(overlap.getStart(), overlap.getEnd());
		} else {
			return Duration.ZERO;
		}
	}
}
