package in.samar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);

		/*
		UserDetailsRepo repo = ctxt.getBean(UserDetailsRepo.class);

		UsersDetailsEntity ude = new UsersDetailsEntity();

		ude.setUserName("pramod");
		ude.setUserEmail("pramod@gmail.com");
		ude.setUserPassword("pramod@23");
		ude.setUserPhone(1235l);

		repo.save(ude);

		System.out.println("inserted");
*/
	}

}
