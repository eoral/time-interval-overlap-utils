package com.eoral.timeintervaloverlaputils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class TimeIntervalOverlapUtilsTest {
	
	@Test(expected = NullPointerException.class)
	public void getOverlapShouldThrowExceptionWhenInterval1IsNull() {
		TimeInterval interval1 = null;
		TimeInterval interval2 = new TimeInterval("2022-04-17T07:30:00Z", "2022-04-17T08:30:00Z");
		TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
	}
	
	@Test(expected = NullPointerException.class)
	public void getOverlapShouldThrowExceptionWhenInterval2IsNull() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = null;
		TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
	}
	
	// According to Allen's interval algebra, there are 13 relations between two intervals.
	// Below tests cover all 13 relations.

	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                                       |------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnEmptyOptionalWhenInterval2StartsAfterInterval1Ends() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T07:30:00Z", "2022-04-17T08:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertTrue(optionalOverlap.isEmpty());
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                                 |------------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnEmptyOptionalWhenInterval2StartsAtTheSameInstantInterval1Ends() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T07:00:00Z", "2022-04-17T08:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertTrue(optionalOverlap.isEmpty());
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                           |------------------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsDuringInterval1AndInterval2EndsAfterInterval1Ends() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:30:00Z", "2022-04-17T08:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:30:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                    |-------------------------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenIntervalsStartsAtTheSameInstantAndInterval2EndsAfterInterval1Ends() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T08:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:              |-------------------------------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsBeforeInterval1StartsAndInterval2EndsAfterInterval1Ends() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T05:30:00Z", "2022-04-17T08:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                          |------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsDuringInterval1AndIntervalsEndAtTheSameInstant() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:30:00Z", "2022-04-17T07:00:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:30:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                    |------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenIntervalsStartAtTheSameInstantAndIntervalsEndAtTheSameInstant() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:              |------------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsBeforeInterval1StartsAndIntervalsEndAtTheSameInstant() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T05:30:00Z", "2022-04-17T07:00:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T07:00:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                        |-----|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsDuringInterval1AndInterval2EndsDuringInterval1() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:15:00Z", "2022-04-17T06:45:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:15:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T06:45:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:                    |---------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenIntervalsStartAtTheSameInstantAndInterval2EndsDuringInterval1() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T06:45:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T06:45:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:              |---------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnNonEmptyOptionalWhenInterval2StartsBeforeInterval1StartsAndInterval2EndsDuringInterval1() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T05:30:00Z", "2022-04-17T06:45:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertFalse(optionalOverlap.isEmpty());
		assertEquals(optionalOverlap.get().getStart().toString(), "2022-04-17T06:00:00Z");
		assertEquals(optionalOverlap.get().getEnd().toString(), "2022-04-17T06:45:00Z");
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2:       |------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnEmptyOptionalWhenInterval2EndsAtTheSameInstantInterval1Starts() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T05:00:00Z", "2022-04-17T06:00:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertTrue(optionalOverlap.isEmpty());
	}
	
	/**
	 * <pre>
	 * Interval-1:                    |------------|
	 * Interval-2: |------------|
	 * </pre>      
	 */
	@Test
	public void getOverlapShouldReturnEmptyOptionalWhenInterval2EndsBeforeInterval1Starts() {
		TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
		TimeInterval interval2 = new TimeInterval("2022-04-17T04:30:00Z", "2022-04-17T05:30:00Z");
		Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
		assertTrue(optionalOverlap.isEmpty());
	}
}
