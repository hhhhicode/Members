package hwangjihun.members;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MembersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MembersApplication.class, args);
	}
}
