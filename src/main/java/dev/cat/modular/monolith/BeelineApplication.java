package dev.cat.modular.monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BeelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeelineApplication.class, args);
	}

}
