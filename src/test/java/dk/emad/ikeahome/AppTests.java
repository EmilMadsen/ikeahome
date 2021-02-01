package dk.emad.ikeahome;

import nl.stijngroenen.tradfri.device.Gateway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AppTests {

	@MockBean
	private Gateway gateway;

	@Test
	void contextLoads() {
	}

}
