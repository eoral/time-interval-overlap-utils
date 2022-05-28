# About
- A simple utility library for finding the overlap between two time intervals.
- Requires Java 1.8 or above. 
- No dependency to other libraries.
- 100% test coverage.

# Usage
```
TimeInterval interval1 = new TimeInterval("2022-04-17T06:00:00Z", "2022-04-17T07:00:00Z");
TimeInterval interval2 = new TimeInterval("2022-04-17T05:30:00Z", "2022-04-17T06:45:00Z");
Optional<TimeInterval> optionalOverlap = TimeIntervalOverlapUtils.getOverlap(interval1, interval2);
if (optionalOverlap.isPresent()) {
	TimeInterval overlap = optionalOverlap.get();
	System.out.println(overlap.getStart() + " - " + overlap.getEnd());
	// Output: 2022-04-17T06:00:00Z - 2022-04-17T06:45:00Z
}
```
