package com.eoral.timeintervaloverlaputils;

import java.time.Duration;
import java.time.Instant;

public class Test {
	
	public static void main(String[] args) {
		Instant start = Instant.parse("2022-04-17T06:00:00Z");
		Instant end = Instant.parse("2022-04-17T07:00:00Z");
		Duration duration = Duration.between(start, end);
		System.out.println(duration);
	}

}
