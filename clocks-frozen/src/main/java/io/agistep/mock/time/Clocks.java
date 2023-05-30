package io.agistep.mock.time;

import java.time.*;

class Clocks {
	public static Clock fixedLocalDateTime(String dateTimeString) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return fixed(instant, ZoneId.systemDefault());
	}

	static Clock fixed(Instant instant, ZoneId zoneId) {
		return Clock.fixed(instant, zoneId);
	}
}
