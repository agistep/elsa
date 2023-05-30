package io.agistep.mock.time;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class ClocksTest {

	@Test
	void fixedLocalDateTime() {
		Clock actual = Clocks.fixedLocalDateTime("2023-05-30T00:00:00");
		assertThat(actual.getZone()).isEqualTo(ZoneId.systemDefault());
		assertThat(actual.instant()).isEqualTo("2023-05-29T15:00:00Z");

		assertThat(LocalDateTime.now(actual)).isEqualTo("2023-05-30T00:00:00");
	}
}