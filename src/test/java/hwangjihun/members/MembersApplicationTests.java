package hwangjihun.members;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.Validator;

@TestPropertySource(locations = "/application.properties")
//@SpringBootTest
class MembersApplicationTests {


	@Test
	void contextLoads() {
		String displayPrograms = "80,90";
		String[] split = displayPrograms.split(",");
		for (String s : split) {
			System.out.println("s = " + s);
		}
	}

}
