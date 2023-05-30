import io.agistep.mock.time.Elsa;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class ElsaFreezeTest {

	public static class Foo {
		LocalDateTime now() {
			return LocalDateTime.now();
		}
	}

	@Test
	void freeze() throws Throwable {
		Foo foo = new Foo();

		Elsa.freeze(Instant.parse("2014-12-22T10:15:30Z"), ZoneId.of("UTC"), () -> {
			assertThat(foo.now()).isEqualTo("2014-12-22T10:15:30");
		});

		Elsa.freeze("2014-12-22T10:15:30", () -> {
			assertThat(foo.now()).isEqualTo("2014-12-22T10:15:30");
		});

	}
}