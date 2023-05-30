package io.agistep.mock.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class ElsaTest {

	static final ZoneId UTC = ZoneId.of("UTC");

	@Test
	@DisplayName("Elsa freezes a \"java.time.Clock#systemDefaultZone\" " +
			"using an instant String and a time Zone.")
	void mocking_systemDefaultZone() throws Throwable {

		final String expected = "2023-05-05T10:15:30Z";
		final Instant instant = Instant.parse(expected);
		final ZoneId utc = UTC;

		Clock before = Clock.systemDefaultZone();
		assertThat(before.getZone()).isNotEqualTo(utc);
		assertThat(before.instant()).isNotEqualTo(Instant.parse(expected));

		Elsa.freeze(instant, utc, () -> {
			Clock actual = Clock.systemDefaultZone();
			assertThat(actual.getZone()).isEqualTo(utc);
			assertThat(actual.instant()).isEqualTo(Instant.parse(expected));
		});

		Clock after = Clock.systemDefaultZone();
		assertThat(after.getZone()).isEqualTo(before.getZone());
		assertThat(after.instant()).isEqualTo(before.instant());
	}

	@Test
	void freezeLocalDateTime() throws Throwable {
		Elsa.freezeLocalDateTime("2023-05-30T00:00:00", ()->{
			Clock actual = Clock.systemDefaultZone();

			assertThat(actual.getZone()).isEqualTo(ZoneId.systemDefault());
			assertThat(LocalDateTime.now()).isEqualTo("2023-05-30T00:00:00");
		});
	}

	@Test
	void freeze() throws Throwable {
		Elsa.freeze("2023-05-30T00:00:00", ()->{
			Clock actual = Clock.systemDefaultZone();

			assertThat(actual.getZone()).isEqualTo(ZoneId.systemDefault());
			assertThat(LocalDateTime.now()).isEqualTo("2023-05-30T00:00:00");
		});
	}
}