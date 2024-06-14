package dev.cat.modular.monolith;

import org.springframework.boot.SpringApplication;

public class TestBeelineApplication {

	public static void main(String[] args) {
		SpringApplication.from(BeelineApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
