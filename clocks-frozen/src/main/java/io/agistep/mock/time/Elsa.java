package io.agistep.mock.time;

import org.mockito.MockedStatic;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.mockStatic;

public final class Elsa {

	public static void freeze(String localDateTimeString, Freezing freezing) throws Throwable {
		freezeLocalDateTime(localDateTimeString, freezing);
	}

	public static void freezeLocalDateTime(String localDateTimeString, Freezing freezing) throws Throwable {
		freeze(Clocks.fixedLocalDateTime(localDateTimeString), freezing);
	}

	public static void freeze(Instant instant, ZoneId zoneId, Freezing freezing) throws Throwable {
		Clock clock = Clocks.fixed(instant, zoneId);
		freeze(clock, freezing);
	}

	public static void freeze(Clock clock, Freezing freezing) throws Throwable {
		try (MockedStatic<Clock> c = mockStatic(Clock.class)) {
			c.when(Clock::systemDefaultZone).thenReturn(clock);
			freezing.doProcess();
		}
	}


}
