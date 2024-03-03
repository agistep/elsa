package io.agistep.mock.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ElsaExtension.class)
public class ElsaExtensionTest {

	@Test
	@Freeze("2014-12-22T10:15:30")
	public void freeze() {
		assertThat(ZoneId.systemDefault()).isEqualTo(Clock.systemDefaultZone().getZone());
		assertThat(LocalDateTime.now()).isEqualTo("2014-12-22T10:15:30");
	}


}